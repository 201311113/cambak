package com.example.cambak.cambak.domains.campsite.service.impl

import com.example.cambak.cambak.common.util.*
import com.example.cambak.cambak.domains.campsite.model.CampsiteDto
import com.example.cambak.cambak.domains.campsite.service.CampsiteService
import com.example.cambak.database.entity.ExternalReview
import com.example.cambak.database.entity.Image
import com.example.cambak.database.entity.ImageType
import com.example.cambak.database.entity.Type
import com.example.cambak.database.entity.place.*
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDateTime
import javax.transaction.Transactional
import kotlin.jvm.Throws

@Service
class CampsiteServiceImpl(
    var repo: RepositoryProvider,
    var awsS3Service: AmazonS3Service,
): CampsiteService{

    override fun getDetail(campsiteId: String): CampsiteDto.GetCampsiteDetailRes {
        TODO("Not yet implemented")
    }
    override fun create(req: CampsiteDto.CreateCampsiteReq): CampsiteDto.CreateCampsiteRes {

        val newCampsite = Campsite(
            name = req.name,
            address = req.address,
            oneLineDescription = req.oneLineDescription,
            description = req.description,
            lat = req.lat,
            lng = req.lng,
            phoneNo = req.phoneNo,
            region = req.region,
            environment = req.environment,
            possibleCarType = req.possibleCarType,
            websiteUrl = req.websiteUrl,
            priceDescription = req.priceDescription,
            mannerTimeDescription = req.mannerTimeDescription,
            campType = req.campType,
            floorGround = req.floorGround,
            campsiteSize = req.campsiteSize,
            campsiteSpace = req.campsiteSpace
        )

        val configList = mutableListOf<PlaceConfigMapping>()

        req.hashTags?.forEach {
            val hashtag = repo.placeConfigRepository.findByPlaceConfigKeyAndPlaceConfigType(it,ConfigType.HASHTAG)
                ?: return CampsiteDto.CreateCampsiteRes(PLACE_CONFIG_NOT_FOUND)
            configList.add(
                PlaceConfigMapping(
                    newCampsite,
                    hashtag
                )
            )
        }
        req.facilities?.forEach {
            val facility = repo.placeConfigRepository.findByPlaceConfigKeyAndPlaceConfigType(it,ConfigType.FACILITIES)
                ?: return CampsiteDto.CreateCampsiteRes(PLACE_CONFIG_NOT_FOUND)
            configList.add(
                PlaceConfigMapping(
                    newCampsite,
                    facility
                )
            )
        }
        req.amenities?.forEach {
            val amenity = repo.placeConfigRepository.findByPlaceConfigKeyAndPlaceConfigType(it,ConfigType.AMENITIES)
                ?: return CampsiteDto.CreateCampsiteRes(PLACE_CONFIG_NOT_FOUND)
            configList.add(
                PlaceConfigMapping(
                    newCampsite,
                    amenity
                )
            )
        }
        req.rules?.forEach {
            val rule = repo.placeConfigRepository.findByPlaceConfigKeyAndPlaceConfigType(it,ConfigType.RULES)
                ?: return CampsiteDto.CreateCampsiteRes(PLACE_CONFIG_NOT_FOUND)
            configList.add(
                PlaceConfigMapping(
                    newCampsite,
                    rule
                )
            )
        }

        val externalReviewList = mutableListOf<ExternalReview>()
        req.blogUrls?.forEach {
            externalReviewList.add(
                ExternalReview(
                    it,
                    Type.BLOG,
                    newCampsite
                )
            )
        }
        req.youtubeUrls?.forEach {
            externalReviewList.add(
                ExternalReview(
                    it,
                    Type.YOUTUBE,
                    newCampsite
                )
            )
        }

        repo.campsiteRepository.save(newCampsite)
        repo.placeConfigMappingRepository.saveAll(configList)
        repo.externalReviewRepository.saveAll(externalReviewList)

        return CampsiteDto.CreateCampsiteRes(
            OK,
            newCampsite.id
        )
    }

    @Transactional
    override fun uploadImages(
        campsiteId: String,
        imageReq: List<MultipartFile>?,
        layoutReq: List<MultipartFile>?,
        deleteImageIds: String?
    ): CampsiteDto.UploadImageRes {
        val campsite = repo.campsiteRepository.findByIdAndActive(campsiteId)
            ?: return CampsiteDto.UploadImageRes(CAMPSITE_NOT_FOUND)
        val oldImageList = repo.imageRepository.findAllByAssociatedEntityId(campsiteId)

        imageReq?.let {
            uploadImages(campsiteId,imageReq,ImageType.CAMPSITE_BASIC)
        }
        layoutReq?.let {
            uploadImages(campsiteId,layoutReq,ImageType.CAMPSITE_LAYOUT)
        }

        deleteImageIds?.let {
            deleteImages(deleteImageIds)
        }

        return CampsiteDto.UploadImageRes(OK)
    }

    @Throws(BadRequestException::class)
    private fun uploadImages(
        campsiteId: String,
        imageReq: List<MultipartFile>?,
        type: ImageType
    ){
        if (imageReq!![0].originalFilename == "") return

        try {
            repo.imageRepository.saveAll(
                imageReq!!.map {
                    val id = CommonUtils.uuid()
                    val s3ImageUrl = awsS3Service.uploadImageS3(
                        it,
                        "campsite/"+ id
                    )
                    Image(
                        url = s3ImageUrl,
                        associatedEntityId = campsiteId,
                        isMain = false,
                        created = LocalDateTime.now(),
                        seq = 0,
                        type = type
                    )
                }
            )
        }catch (e: Exception){
            throw BadRequestException(IMAGE_UPLOAD_FAIL)
        }
    }
    @Throws(BadRequestException::class)
    private fun deleteImages(
        deleteImageIds: String?
    ){
        val deleteImageList = deleteImageIds!!.split(",")

        repo.imageRepository.deleteAllInIds(deleteImageList)
        awsS3Service.deleteImagesS3(deleteImageList)
    }

    override fun update(req: CampsiteDto.UpdateCampsiteReq): CampsiteDto.UpdateCampsiteRes {
        val campsite = repo.campsiteRepository.findByIdAndActive(req.id)
            ?: return CampsiteDto.UpdateCampsiteRes(CAMPSITE_NOT_FOUND)
        req.name?.let { campsite.name = req.name!! }
        req.address?.let { campsite.address = req.address!! }
        req.oneLineDescription?.let { campsite.oneLineDescription = req.oneLineDescription!! }
        req.description?.let { campsite.description = req.description!! }
        req.lat?.let { campsite.lat = req.lat!! }
        req.lng?.let { campsite.lng = req.lng!! }
        req.phoneNo?.let { campsite.phoneNo = req.phoneNo!! }
        req.region?.let { campsite.region = req.region!! }
        req.environment?.let { campsite.environment = req.environment!! }
        req.possibleCarType?.let { campsite.possibleCarType = req.possibleCarType!! }
        req.websiteUrl?.let { campsite.websiteUrl = req.websiteUrl!! }
        req.priceDescription?.let { campsite.priceDescription = req.priceDescription!! }
        req.mannerTimeDescription?.let { campsite.mannerTimeDescription = req.mannerTimeDescription!! }
        req.campType?.let { campsite.campType = req.campType!! }
        req.floorGround?.let { campsite.floorGround = req.floorGround!! }
        req.campsiteSize?.let { campsite.campsiteSize = req.campsiteSize!! }
        req.campsiteSpace?.let { campsite.campsiteSpace = req.campsiteSpace!! }


        //여기부터 연관관계 있는 table 수정 사항
        val configList = mutableListOf<PlaceConfigMapping>()
        //TODO: foreach말고 한줄로 update all할 수 있는 로직을 짜... 야되는데... ㄱㅊ
        req.hashTags?.forEach {
            val config = repo.placeConfigRepository.findByPlaceConfigKeyAndPlaceConfigType(it,ConfigType.HASHTAG)
                ?: return CampsiteDto.UpdateCampsiteRes(PLACE_CONFIG_NOT_FOUND)
            configList.add(
                PlaceConfigMapping(
                    campsite,
                    config
                )
            )
        }
        req.facilities?.forEach {
            val config = repo.placeConfigRepository.findByPlaceConfigKeyAndPlaceConfigType(it, ConfigType.FACILITIES)
                ?:return CampsiteDto.UpdateCampsiteRes(PLACE_CONFIG_NOT_FOUND)
            configList.add(
                PlaceConfigMapping(
                    campsite,
                    config
                )
            )
        }
        req.amenities?.forEach {
            val config = repo.placeConfigRepository.findByPlaceConfigKeyAndPlaceConfigType(it, ConfigType.AMENITIES)
                ?:return CampsiteDto.UpdateCampsiteRes(PLACE_CONFIG_NOT_FOUND)
            configList.add(
                PlaceConfigMapping(
                    campsite,
                    config
                )
            )
        }
        req.rules?.forEach {
            val config = repo.placeConfigRepository.findByPlaceConfigKeyAndPlaceConfigType(it, ConfigType.RULES)
                ?:return CampsiteDto.UpdateCampsiteRes(PLACE_CONFIG_NOT_FOUND)
            configList.add(
                PlaceConfigMapping(
                    campsite,
                    config
                )
            )
        }


        val externalReviewList = mutableListOf<ExternalReview>()
        req.blogUrls?.forEach {
            externalReviewList.add(
                ExternalReview(
                    it,
                    Type.BLOG,
                    campsite
                )
            )
        }
        req.youtubeUrls?.forEach {
            externalReviewList.add(
                ExternalReview(
                    it,
                    Type.YOUTUBE,
                    campsite
                )
            )
        }

        repo.placeConfigMappingRepository.deleteAllByPlace(campsite)
        repo.externalReviewRepository.deleteAllByPlace(campsite)

        repo.campsiteRepository.save(campsite)
        repo.placeConfigMappingRepository.saveAll(configList)
        repo.externalReviewRepository.saveAll(externalReviewList)

        return CampsiteDto.UpdateCampsiteRes(
            OK,
            campsite.id
        )
    }


//    override fun delete(req: CampsiteDto.DeleteCampsiteReq): CampsiteDto.DeleteCampsiteRes {
//        TODO("Not yet implemented")
//    }
}
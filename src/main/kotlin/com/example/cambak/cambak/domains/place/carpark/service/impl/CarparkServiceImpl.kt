package com.example.cambak.cambak.domains.place.carpark.service.impl

import com.example.cambak.cambak.common.util.*
import com.example.cambak.cambak.domains.place.campsite.model.CampsiteDto
import com.example.cambak.cambak.domains.place.carpark.model.CarparkDto
import com.example.cambak.cambak.domains.place.carpark.service.CarparkService
import com.example.cambak.database.entity.ExternalReview
import com.example.cambak.database.entity.Image
import com.example.cambak.database.entity.ImageType
import com.example.cambak.database.entity.Type
import com.example.cambak.database.entity.place.Carpark
import com.example.cambak.database.entity.place.ConfigType
import com.example.cambak.database.entity.place.PlaceConfigMapping
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDateTime
import javax.transaction.Transactional
import kotlin.jvm.Throws

@Service
class CarparkServiceImpl(
    var repo: RepositoryProvider,
    var awsS3Service: AmazonS3Service,
): CarparkService {
    override fun create(req: CarparkDto.CreateCarparkReq): CarparkDto.CreateCarparkRes {
        val newCarpark = Carpark(
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
        )
        val configList = mutableListOf<PlaceConfigMapping>()

        req.hashTags?.forEach {
            val hashtag = repo.placeConfigRepository.findByPlaceConfigKeyAndPlaceConfigType(it, ConfigType.HASHTAG)
                ?: return CarparkDto.CreateCarparkRes(PLACE_CONFIG_NOT_FOUND)
            configList.add(
                PlaceConfigMapping(
                    newCarpark,
                    hashtag
                )
            )
        }
        req.facilities?.forEach {
            val facility = repo.placeConfigRepository.findByPlaceConfigKeyAndPlaceConfigType(it, ConfigType.FACILITIES)
                ?: return CarparkDto.CreateCarparkRes(PLACE_CONFIG_NOT_FOUND)
            configList.add(
                PlaceConfigMapping(
                    newCarpark,
                    facility
                )
            )
        }
        req.amenities?.forEach {
            val amenity = repo.placeConfigRepository.findByPlaceConfigKeyAndPlaceConfigType(it, ConfigType.AMENITIES)
                ?: return CarparkDto.CreateCarparkRes(PLACE_CONFIG_NOT_FOUND)
            configList.add(
                PlaceConfigMapping(
                    newCarpark,
                    amenity
                )
            )
        }
        req.rules?.forEach {
            val rule = repo.placeConfigRepository.findByPlaceConfigKeyAndPlaceConfigType(it, ConfigType.RULES)
                ?: return CarparkDto.CreateCarparkRes(PLACE_CONFIG_NOT_FOUND)
            configList.add(
                PlaceConfigMapping(
                    newCarpark,
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
                    newCarpark
                )
            )
        }
        req.youtubeUrls?.forEach {
            externalReviewList.add(
                ExternalReview(
                    it,
                    Type.YOUTUBE,
                    newCarpark
                )
            )
        }

        repo.carparkRepository.save(newCarpark)
        repo.placeConfigMappingRepository.saveAll(configList)
        repo.externalReviewRepository.saveAll(externalReviewList)

        return CarparkDto.CreateCarparkRes(
            OK,
            newCarpark.id!!
        )
    }

    override fun update(req: CarparkDto.UpdateCarparkReq): CarparkDto.UpdateCarparkRes {
        val carpark = repo.carparkRepository.findByIdAndActive(req.id)
            ?: return CarparkDto.UpdateCarparkRes(CARPARK_NOT_FOUND)

        req.name?.let { carpark.name = req.name!! }
        req.address?.let { carpark.address = req.address!! }
        req.oneLineDescription?.let { carpark.oneLineDescription = req.oneLineDescription!! }
        req.description?.let { carpark.description = req.description!! }
        req.lat?.let { carpark.lat = req.lat!! }
        req.lng?.let { carpark.lng = req.lng!! }
        req.phoneNo?.let { carpark.phoneNo = req.phoneNo!! }
        req.region?.let { carpark.region = req.region!! }
        req.environment?.let { carpark.environment = req.environment!! }
        req.possibleCarType?.let { carpark.possibleCarType = req.possibleCarType!! }

        //여기부터 연관관계 있는 table 수정 사항
        val configList = mutableListOf<PlaceConfigMapping>()
        //TODO: foreach말고 한줄로 update all할 수 있는 로직을 짜... 야되는데... ㄱㅊ
        req.hashTags?.forEach {
            val config = repo.placeConfigRepository.findByPlaceConfigKeyAndPlaceConfigType(it,ConfigType.HASHTAG)
                ?: return CarparkDto.UpdateCarparkRes(PLACE_CONFIG_NOT_FOUND)
            configList.add(
                PlaceConfigMapping(
                    carpark,
                    config
                )
            )
        }
        req.facilities?.forEach {
            val config = repo.placeConfigRepository.findByPlaceConfigKeyAndPlaceConfigType(it, ConfigType.FACILITIES)
                ?:return CarparkDto.UpdateCarparkRes(PLACE_CONFIG_NOT_FOUND)
            configList.add(
                PlaceConfigMapping(
                    carpark,
                    config
                )
            )
        }
        req.amenities?.forEach {
            val config = repo.placeConfigRepository.findByPlaceConfigKeyAndPlaceConfigType(it, ConfigType.AMENITIES)
                ?:return CarparkDto.UpdateCarparkRes(PLACE_CONFIG_NOT_FOUND)
            configList.add(
                PlaceConfigMapping(
                    carpark,
                    config
                )
            )
        }
        req.rules?.forEach {
            val config = repo.placeConfigRepository.findByPlaceConfigKeyAndPlaceConfigType(it, ConfigType.RULES)
                ?:return CarparkDto.UpdateCarparkRes(PLACE_CONFIG_NOT_FOUND)
            configList.add(
                PlaceConfigMapping(
                    carpark,
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
                    carpark
                )
            )
        }
        req.youtubeUrls?.forEach {
            externalReviewList.add(
                ExternalReview(
                    it,
                    Type.YOUTUBE,
                    carpark
                )
            )
        }

        repo.placeConfigMappingRepository.deleteAllByPlace(carpark)
        repo.externalReviewRepository.deleteAllByPlace(carpark)

        repo.carparkRepository.save(carpark)
        repo.placeConfigMappingRepository.saveAll(configList)
        repo.externalReviewRepository.saveAll(externalReviewList)

        return CarparkDto.UpdateCarparkRes(
            OK,
            carpark.id
        )
    }

    @Transactional
    override fun uploadImages(
        carparkId: String,
        imageReq: List<MultipartFile>?,
        deleteImageIds: String?
    ): CarparkDto.UploadImageRes {
        val carpark = repo.carparkRepository.findByIdAndActive(carparkId)
            ?: return CarparkDto.UploadImageRes(CARPARK_NOT_FOUND)

        imageReq?.let {
            uploadImages(carparkId,imageReq,ImageType.CAMPSITE_BASIC)
        }

        deleteImageIds?.let {
            deleteImages(deleteImageIds)
        }

        return CarparkDto.UploadImageRes(
            OK
        )
    }
    @Throws(BadRequestException::class)
    private fun uploadImages(
        carparkId: String,
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
                        "carpark/"+ id
                    )
                    Image(
                        url = s3ImageUrl,
                        associatedEntityId = carparkId,
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

    override fun getDetail(carparkId: String): CarparkDto.GetCarparkDetail {
        val carpark = repo.carparkRepository.findByIdAndActive(carparkId)
            ?: return CarparkDto.GetCarparkDetail(CARPARK_NOT_FOUND)

        return CarparkDto.GetCarparkDetail(
            OK,
            buildCarparkDetail(carpark)
        )
    }

    @Throws(BadRequestException::class)
    private fun buildCarparkDetail(
        carpark: Carpark
    ): CarparkDto.GetCarparkDetail.CarparkDetail{

        val imageUrlList = repo.imageRepository.findAllByAssociatedEntityId(carpark.id!!)?.map { it.url }

        //placeConfig build (기본, 부대시설, hashtag)
        val placeConfigList = carpark.placeConfigList
        val hashtags = placeConfigList
            ?.filter { it.placeConfig.placeConfigType == ConfigType.HASHTAG }
            ?.map { it.placeConfig.placeConfigKey }
        val basicConfigList = placeConfigList
            ?.filter { it.placeConfig.placeConfigType == ConfigType.FACILITIES }
            ?.map { it.placeConfig.placeConfigKey }
        val amenityConfigList = placeConfigList
            ?.filter { it.placeConfig.placeConfigType == ConfigType.AMENITIES }
            ?.map { it.placeConfig.placeConfigKey }


        val externalReviewList = carpark.externalReviewList
        val blogUrlList = externalReviewList
            ?.filter { it.type == Type.BLOG }
            ?.map { it.url }
        val youtubeUrls = externalReviewList
            ?.filter { it.type == Type.YOUTUBE }
            ?.map { it.url }

        return CarparkDto.GetCarparkDetail.CarparkDetail(
            id = carpark.id!!,
            name = carpark.name,
            placeType = carpark.getPlaceType().toString(),
            environment = carpark.environment,
            address = carpark.address,
            region = carpark.region,
            imageUrlList = imageUrlList,
            oneLineDescription = carpark.oneLineDescription,
            hashTags = hashtags,
            description = carpark.description,
            basicConfigList = basicConfigList,
            amenityConfigList = amenityConfigList,
            possibleCarType = CommonUtils.calCarTypeBinaryToString(carpark.possibleCarType),
            lat = carpark.lat,
            lng = carpark.lng,
            phoneNo = carpark.phoneNo,
            score = carpark.totalScore.toDouble() / carpark.reviewer.toDouble(),
            isLiked = false, //TODO: 다음기획 추후개발
            blogUrlList = blogUrlList,
            youtubeUrls = youtubeUrls,
        )
    }
}
package com.example.cambak.cambak.domains.campsite.service.impl

import com.example.cambak.cambak.common.util.CAMPSITE_NOT_FOUND
import com.example.cambak.cambak.common.util.OK
import com.example.cambak.cambak.common.util.RepositoryProvider
import com.example.cambak.cambak.domains.campsite.model.CampsiteDto
import com.example.cambak.cambak.domains.campsite.service.CampsiteService
import com.example.cambak.database.entity.place.Campsite
import org.springframework.stereotype.Service

@Service
class CampsiteServiceImpl(
    var repo: RepositoryProvider
): CampsiteService{
    override fun create(req: CampsiteDto.CreateCampsiteReq): CampsiteDto.CreateCampsiteRes {
        val newCampsite = Campsite(
            name = req.name,
            address = req.address,
            addressDetail = req.addressDetail,
            lat = req.lat,
            lng = req.lng
        )
        repo.campsiteRepository.save(newCampsite)
        return CampsiteDto.CreateCampsiteRes(
            OK,
            newCampsite.id
        )
    }

    override fun update(req: CampsiteDto.UpdateCampsiteReq): CampsiteDto.UpdateCampsiteRes {
        val campsite = repo.campsiteRepository.findByIdAndActive(req.id)
            ?: return CampsiteDto.UpdateCampsiteRes(CAMPSITE_NOT_FOUND)
        req.name?.let { campsite.name = req.name!! }
        req.address?.let { campsite.address = req.address!! }
        req.addressDetail?.let { campsite.addressDetail = req.addressDetail!! }
        req.lat?.let { campsite.lat = req.lat!! }
        req.lng?.let { campsite.lng = req.lng!! }

        repo.campsiteRepository.save(campsite)

        return CampsiteDto.UpdateCampsiteRes(
            OK,
            campsite.id
        )
    }

    override fun getList(): CampsiteDto.GetCampsiteListRes {
        val camsiteList = repo.campsiteRepository.findAllByActive()
            ?: return CampsiteDto.GetCampsiteListRes(CAMPSITE_NOT_FOUND)

        return CampsiteDto.GetCampsiteListRes(
            OK,
            buildCampsiteList(camsiteList)
        )
    }
    private fun buildCampsiteList(
        campsiteList: List<Campsite>
    ):List<CampsiteDto.GetCampsiteListRes.CampsiteInfo>{
        return campsiteList.stream().map {
            CampsiteDto.GetCampsiteListRes.CampsiteInfo(
                id = it.id!!,
                name = it.name,
                address = it.address,
                addressDetail = it.addressDetail,
                lat = it.lat,
                lng = it.lng
            )
        }.toList()
    }

    override fun getDetail(campsiteId: String): CampsiteDto.GetCampsiteDetailRes {
        TODO("Not yet implemented")
    }

    override fun delete(req: CampsiteDto.DeleteCampsiteReq): CampsiteDto.DeleteCampsiteRes {
        TODO("Not yet implemented")
    }
}
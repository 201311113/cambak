package com.example.cambak.cambak.domains.campingcar_review.controller

import com.example.cambak.cambak.common.util.BadRequestException
import com.example.cambak.cambak.common.util.ServiceProvider
import com.example.cambak.cambak.domains.campingcar_review.model.CampingCarReviewDto
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/camping-car/review")
@Api(tags = ["[CampingCar Review] 캠핑카 리뷰 관련 API"])
class CampingCarReviewController {

    @Autowired
    lateinit var serviceProvider: ServiceProvider

    @ApiOperation(
        value = "캠핑카 리뷰 등록",
        notes = """
            code 0 : 성공
            code -1: 알 수 없는 오류
        """
    )
    @PostMapping("/create")
    fun create(
        @RequestBody req: CampingCarReviewDto.CreateReviewReq
    ): CampingCarReviewDto.CreateReviewRes{
        try{
            return serviceProvider.campingCarReviewService.create(req)
        }catch (e: BadRequestException){
            return CampingCarReviewDto.CreateReviewRes(e.code)
        }
    }

    @ApiOperation(
        value = "캠핑카 리뷰 수정",
        notes = """
            code 0 : 성공
            code -1: 알 수 없는 오류
        """
    )
    @PutMapping("/update")
    fun update(
        @RequestBody req: CampingCarReviewDto.UpdateReviewReq
    ):CampingCarReviewDto.UpdateReviewRes{
        try{
            return serviceProvider.campingCarReviewService.update(req)

        }catch (e: BadRequestException){
            return CampingCarReviewDto.UpdateReviewRes(e.code)
        }
    }

    @ApiOperation(
        value = "캠핑카 리뷰 리스트업",
        notes = """
            code 0 : 성공
            code -1: 알 수 없는 오류
        """
    )
    @GetMapping("/list")
    fun getReviewList(

    ){

    }


    @ApiOperation(
        value = "캠핑카 리뷰 상세조회",
        notes = """
            code 0 : 성공
            code -1: 알 수 없는 오류
        """
    )
    @GetMapping("/{reviewId}")
    fun getReviewDetail(
        @PathVariable reviewId: String
    ){

    }
}
package com.example.cambak.cambak.domains.campingcar_review.model

import com.example.cambak.cambak.common.util.Response

class CampingCarReviewDto {
    //리뷰 등록

    class CreateReviewReq(
        var title: String,
        var content: String,
        var campingCarId: String
    )
    class CreateReviewRes(
        code: Int,
        var reviewId: String ?= null
    ):Response(code)

    //리뷰 수정
    class UpdateReviewReq(
        var title: String?,
        var content: String?,
        var reviewId: String
    )
    class UpdateReviewRes(
        code: Int,
        var reviewId: String ?= null
    ):Response(code)

    //리뷰 이미지 등록 및 수정

    //리뷰 리스트업
    class GetReviewListReq(

    )
    class GetReviewListRes(
        code: Int
    ):Response(code)
    //리뷰 상세조회
    class GetReviewDetailReq(

    )
    class GetReviewDetailRes(
        code: Int
    ):Response(code)

    //리뷰 삭제
    class DeleteReviewReq(

    )
    class DeleteReviewRes(
        code: Int
    ):Response(code)

}
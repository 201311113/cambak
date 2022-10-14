package com.example.cambak.cambak.domains.campingcar_review.service

import com.example.cambak.cambak.domains.campingcar_review.model.CampingCarReviewDto

interface CampingCarReviewService {
    fun create(
        req: CampingCarReviewDto.CreateReviewReq
    ):CampingCarReviewDto.CreateReviewRes

    fun update(
        req: CampingCarReviewDto.UpdateReviewReq
    ):CampingCarReviewDto.UpdateReviewRes

    fun getReviewList(

    ):CampingCarReviewDto.GetReviewListRes

    fun getReviewDetail(
        reviewId: String
    ):CampingCarReviewDto.GetReviewDetailRes
}
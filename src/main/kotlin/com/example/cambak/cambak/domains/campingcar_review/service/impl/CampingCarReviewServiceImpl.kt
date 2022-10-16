package com.example.cambak.cambak.domains.campingcar_review.service.impl

import com.example.cambak.cambak.common.util.*
import com.example.cambak.cambak.domains.campingcar_review.model.CampingCarReviewDto
import com.example.cambak.cambak.domains.campingcar_review.service.CampingCarReviewService
import com.example.cambak.database.entity.campingcar.review.CampingCarReview
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class CampingCarReviewServiceImpl (
    var repo: RepositoryProvider,

    ): CampingCarReviewService{
    override fun create(req: CampingCarReviewDto.CreateReviewReq): CampingCarReviewDto.CreateReviewRes {
        //validate
        val context = SecurityContextHolder.getContext()
        val user = repo.userRepository.findByEmailAndActive(context.authentication.name)
            ?: return CampingCarReviewDto.CreateReviewRes(USER_NOT_FOUND)

        val campingCar = repo.campingCarRepository.findByIdAndActive(req.campingCarId)
            ?: return CampingCarReviewDto.CreateReviewRes(CAMPING_CAR_NOT_FOUND)

        val newReview = CampingCarReview(
            user = user,
            campingCar = campingCar,
            title = req.title,
            content = req.content,
        )

        repo.campingCarReviewRepository.save(newReview)

        return CampingCarReviewDto.CreateReviewRes(
            OK,
            newReview.id
        )
    }

    override fun update(req: CampingCarReviewDto.UpdateReviewReq): CampingCarReviewDto.UpdateReviewRes {
        //validate
        val context = SecurityContextHolder.getContext()
        val user = repo.userRepository.findByEmailAndActive(context.authentication.name)
            ?: return CampingCarReviewDto.UpdateReviewRes(USER_NOT_FOUND)
        val review = repo.campingCarReviewRepository.findByIdAndActive(req.reviewId)
            ?: return CampingCarReviewDto.UpdateReviewRes(CAMPING_CAR_REVIEW_NOT_FOUND)

        //TODO: ** validate userid != review.user.id

        req.title?.let { review.title = req.title!! }
        req.content?.let { review.content = req.content!! }

        repo.campingCarReviewRepository.save(review)

        return CampingCarReviewDto.UpdateReviewRes(
            OK,
            review.id
        )
    }

    override fun getReviewList(): CampingCarReviewDto.GetReviewListRes {
        val reviewList = repo.campingCarReviewRepository.findAllByActive()
            ?.stream()?.map {
                CampingCarReviewDto.GetReviewListRes.ReviewInfo(
                    id = it.id!!,
                    title = it.title,
                    content = it.content
                )
            }?.toList()
        return CampingCarReviewDto.GetReviewListRes(
            OK,
            reviewList
        )
    }

    override fun getReviewDetail(
        reviewId: String
    ): CampingCarReviewDto.GetReviewDetailRes {
        val review = repo.campingCarReviewRepository.findByIdAndActive(reviewId)
            ?: return CampingCarReviewDto.GetReviewDetailRes(CAMPING_CAR_REVIEW_NOT_FOUND)

        return CampingCarReviewDto.GetReviewDetailRes(
            OK,
            buildReviewDetail(review)
        )
    }
    private fun buildReviewDetail(
        review: CampingCarReview
    ): CampingCarReviewDto.GetReviewDetailRes.ReviewDetail{
        return CampingCarReviewDto.GetReviewDetailRes.ReviewDetail(
            id = review.id!!,
            title = review.title,
            content = review.content
        )
    }

}
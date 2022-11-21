package com.example.cambak.database.entity.place

import com.example.cambak.database.BaseEntity
import com.example.cambak.database.entity.ExternalReview
import java.time.LocalDateTime
import javax.persistence.*


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "place_type")
class Place (

    // 차박지, 캠핑장, 스테이션등 장소관련 공통 데이터
    // 장소 타입 관계없이 로드되는 정보들위주로 포함함
    var name: String,
    var address: String,
    var oneLineDescription: String,
    @Enumerated(value = EnumType.STRING)
    var region: Region,
    @Lob
    var description: String,
    var phoneNo: String,
    var lat: Double,
    var lng: Double,
    var reviewer: Long,
    var totalScore: Long,
    var possibleCarType: String,
    @Enumerated(value = EnumType.STRING)
    var environment: Environment,

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "place")
    var externalReviewList: MutableList<ExternalReview> ?= null,

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "place")
    var placeConfigList: MutableList<PlaceConfigMapping> ?= null,

    //++ 이미지 카테고리가 많아 관계 맺지 않음 Image.class

    //TODO:관련 리뷰 및 영상 관리?

): BaseEntity(

){
    fun getPlaceType(): PlaceType{
        return PlaceType.NONE
    }

}
enum class Environment(text: String){
    SEA("바다"),VALLEY("계곡(강/호수)"), MT("산")
}

enum class PlaceType(text: String){
    CAMPSITE("캠핑장"),CARPARK("차박지"),STATION("스테이션"),TOUR("관광지"),NONE("값없음")
}
enum class Region(text: String){
    SEOULANDGYEONGGI("서울/경기"),INCHEON("인천"),GANGWON("강원"),CHUNGBUK("충북"),CHUNGNAM("충남"),
    GYEONGNAM("경남"),GYEONGBUK("경북"),JEONBUK("전북"),JEONNAM("전남"),JEJU("제주"), UNKNOWN("미식별")
}
package com.example.cambak.database.entity.place

import com.example.cambak.database.BaseEntity
import java.time.LocalDateTime
import javax.persistence.*


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "place_type")
class Place (

    var name: String,
    var address: String,
    var oldAddress: String,
    var bio: String?,
    var isClosed: Boolean,
    var lat: Double,
    var lng: Double,
    var likes: Long,
    var price: Long?,
    var priceDescription: String?,
    var contact: String?,
    var websiteUrl: String?,
    //가중치
    var score: Long,
    var view: Long,
    //TODO:추후 enum화?
    var themeId: String?,
    @Enumerated(value = EnumType.STRING)
    var region: Region?,
    //TODO: 추후 제약조건 걸지 판단
    var mainImageId: String?,
    var search: String?,
    var filter: String,
    var isCampsite: Boolean,
): BaseEntity(

){

}
enum class Region(text: String){
    SEOUL("서울"), GYEONGGI("경기"), JEOLLA("전라"),JEJU("제주"), GYEONGNAM("경남"),GYEONGBUK("경북"),GANGWON("강원"),
    CHUNGCHEONG("충청"), DAEGU("대구"),INCHEON("인천"), DAEJEON("대전"), BUSAN("부산"), GWANGJU("광주"),UNKNOWN("미식별")
}
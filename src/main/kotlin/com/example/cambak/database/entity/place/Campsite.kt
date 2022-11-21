package com.example.cambak.database.entity.place

import com.example.cambak.database.entity.ExternalReview
import javax.persistence.*

@Entity
@DiscriminatorValue(value = "campsite")
@Table(name = "campsite")
class Campsite (

    name: String,
    address: String,
    oneLineDescription: String,
    description: String,
    lat: Double,
    lng: Double,
    phoneNo: String,
    region: Region,
    environment: Environment,

    @Column(length = 4)
    override var possibleCarType: String,    //in server에서 바이너리하게 관리 [일반차량, 트레일러, 카라반, 모터훔] ex) 1011 = [일반차량, 카라반, 모터훔]
    var websiteUrl: String?,
    var priceDescription: String?,
    var mannerTimeDescription: String?,
    @Enumerated(value = EnumType.STRING)
    var campType: CampType, //[운영 형태 : 오토캠핑, 글램핑, 카라반]
    @Enumerated(value = EnumType.STRING)
    var floorGround: FloorGround,   //    항목: [마사토], [데크], [야자매트], [콘크리트], [파쇄석], [자갈], [잔디], [기타]    표시(타입) : 아이콘(img) + 지면 종류(txt)
    var campsiteSize: String,   // “[nn] X [nn]m”(txt)
    var campsiteSpace: String   // “[NN]m” (txt)
    //++ 배치도는 id로 매핑된 image.class에 type = CAMPSITE_LAYOUT

    ): Place(
    name = name,
    address = address,
    oneLineDescription = oneLineDescription,
    region = region,
    description = description,
    phoneNo = phoneNo,
    lat = lat,
    lng = lng,
    reviewer = 0,
    totalScore = 0,
    environment = environment,
    possibleCarType = possibleCarType
){
    override fun getPlaceType(): PlaceType {
        return PlaceType.CAMPSITE
    }
}

enum class CampType(text: String){
    AUTO_CAMPING("오토캠핑"), GLAMPING("글램핑"), CARAVAN("카라반")
}

enum class FloorGround(text: String){//바닥지면
    MASATO("마사토"), DECK("데크"), PALM_MAT("야자매트"), CONCRETE("콘크리트"),
    CRUSHED_STONE("파쇄석"),PEBBLE("자갈"), GRASS("잔디"), OTHERS("기타")
}
package com.example.cambak.cambak.domains.campingcar.model

class CampingCarConfigDto {
    class AddConfigReq(
        //영문 id
        var configKey: String,
        //실제 client에 표시될 word(한, 영 모두 가능)
        var configName: String,
        var configType: String,
    )
    class AddConfigTypeReq(
        var type: String,
    )

}
package com.example.cambak.database.entity.campingcar.reservation

import com.example.cambak.database.BaseEntity
import com.example.cambak.database.entity.CampingCar
import com.example.cambak.database.entity.User
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class CampingCarReservation (
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "camping_car_id", nullable = false)
    val campingCar: CampingCar,

    var rentalTime: String,
    var returnTime: String,
    var basicConfigList: String, // ','로 split되는 string 형태 ex) aircondition,bed,...
    @Enumerated(value = EnumType.STRING)
    var status: ReservationStatus
    //TODO: reservation option - camping car의 선택 옵션 택1가능한거는 칼럼으로 여러개 선택 옵션은 list(basic config?) or table화

    ):BaseEntity()

enum class ReservationStatus(description: String){
    //TODO: 예약상태 초기화 할 것
    INIT("초기상태")
}
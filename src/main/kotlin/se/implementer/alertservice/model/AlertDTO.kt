package se.implementer.alertservice.model

import java.time.LocalDateTime

data class AlertDTO(
    val id: Long,
    val alertId: Long,
    val message: String,
    val type: AlertType,
    val level: LevelDto,
    val created: LocalDateTime,
)

{
    companion object {
        fun mapToAlertDto(alert: Alert) : AlertDTO =
            AlertDTO(
                id = alert.id!!,
                alertId = alert.alertId,
                message = alert.msg,
                type = alert.type,
                level = alert.level.mapToLevelDto(),
                created = alert.created,
            )
    }
}

enum class LevelDto{
    LOW,
    MEDIUM,
    HIGH,
    SEVERE,
    RESOLVED,
}

fun Level.mapToLevelDto(): LevelDto {
    return when (this) {
        Level.LOW -> LevelDto.LOW
        Level.MEDIUM -> LevelDto.MEDIUM
        Level.HIGH -> LevelDto.HIGH
        Level.SEVERE -> LevelDto.SEVERE
        Level.RESOLVED -> LevelDto.RESOLVED
    }
}

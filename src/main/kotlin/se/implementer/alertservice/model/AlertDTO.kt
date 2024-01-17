package se.implementer.alertservice.model

import java.time.LocalDateTime

data class AlertDTO(
    val id: Long,
    val alertId: Long,
    val message: String,
    val type: AlertType,
    val level: Level,
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
                level = alert.level,
                created = alert.created,
            )
    }
}

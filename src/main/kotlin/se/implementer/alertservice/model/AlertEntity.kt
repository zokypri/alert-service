package se.implementer.alertservice.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("alerts")
data class Alert(
    @Id
    val id: Long? = null,

    @Column("alert_id")
    val alertId: Long,

    @Column("msg")
    val msg: String,

    @Column("type")
    val type: AlertType,

    @Column("level")
    val level: Level,
){
    companion object {
        fun mapToAlert(policeWarning: PoliceWarning): Alert =
            Alert(
                alertId = policeWarning.id,
                msg = policeWarning.msg,
                type = AlertType.POLICE,
                level = Level.MEDIUM,
            )
    }
}

enum class AlertType {
    POLICE,
    MILITARY,
    FIRE,
}

enum class Level {
    LOW,
    MEDIUM,
    HIGH,
    SEVERE,
}

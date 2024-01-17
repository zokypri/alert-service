package se.implementer.alertservice.kafka

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.util.logging.Logger
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import se.implementer.alertservice.model.Alert
import se.implementer.alertservice.model.PoliceWarning
import se.implementer.alertservice.repository.AlertRepository

@Component
class KafkaListener (val alertRepository: AlertRepository){

    private val logger = Logger.getLogger(KafkaListener::class.java.name)

    @KafkaListener(topics = [Police_WAGNING_TOPIC])
    @Transactional
    fun policeWarningListener(event: String) {
        logger.info("Received Kafka event from topic $Police_WAGNING_TOPIC with event $event")
        val policeWarning = jacksonObjectMapper().readValue(event, PoliceWarning::class.java)
        logger.info("Kafka event is mapped and ready to be stored in the DB with event message $policeWarning")
        alertRepository.save(Alert.mapToAlert(policeWarning))
        logger.info("Kafka event is  stored in the DB table Alert")
    }

    companion object {
        private const val Police_WAGNING_TOPIC = "PoliceWarning"
    }
}
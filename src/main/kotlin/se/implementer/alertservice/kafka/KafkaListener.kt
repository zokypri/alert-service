package se.implementer.alertservice.kafka

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import se.implementer.alertservice.model.Alert
import se.implementer.alertservice.model.PoliceWarning
import se.implementer.alertservice.repository.AlertRepository

@Component
class KafkaListener (val alertRepository: AlertRepository){

    @KafkaListener(topics = ["PoliceWarning"])
    @Transactional
    fun policeWarningListener(event: String) {
        val policeWarning = jacksonObjectMapper().readValue(event, PoliceWarning::class.java)
        alertRepository.save(Alert.mapToAlert(policeWarning))
    }
}
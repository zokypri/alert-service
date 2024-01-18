package se.implementer.alertservice.service

import java.util.logging.Logger
import org.springframework.stereotype.Service
import se.implementer.alertservice.controller.AlertController
import se.implementer.alertservice.exception.WarningAlertNotFoundException
import se.implementer.alertservice.model.Alert
import se.implementer.alertservice.model.AlertDTO
import se.implementer.alertservice.model.Level
import se.implementer.alertservice.repository.AlertRepository

@Service
class AlertService(val alertRepository: AlertRepository) {

    private val logger = Logger.getLogger(AlertController::class.java.name)

    fun fetchAlerts(): List<AlertDTO> {
        logger.info("Fetching warning alerts from DB")
        return alertRepository
            .findAll()
            .toList()
            .map {
                    alert -> AlertDTO.mapToAlertDto(alert)
            }
    }

    fun fetchAlertsById(alertId: Long): List<AlertDTO> {
        logger.info("Fetching warning alerts from DB for alert id: $alertId")
        val warningAlerts = alertRepository.findAllByAlertId(alertId)
            .takeIf { it.isNotEmpty() } ?: throw WarningAlertNotFoundException(alertId)
        return warningAlerts
            .map {
                AlertDTO.mapToAlertDto(it)
            }
    }

    fun fetchNotResolvedAlerts(): List<AlertDTO> {
        //This would be best to fetch filtered by not resolved from the DB but I filter it using Kotlin instead for this small project
        // since this is a private educational project it is easier and more fun to write Kotlin code than having DB do everything
        logger.info("Fetching warning alerts that have not been resolved")
        val warningAlerts = alertRepository.findAll() .toList()
        val resolvedAlertId = fetchAllResolvedAlertId(warningAlerts)
        return warningAlerts
            .filterNot { it.alertId in resolvedAlertId }
            .map { AlertDTO.mapToAlertDto(it) }
    }

    private fun fetchAllResolvedAlertId(warningAlerts: List<Alert>): List<Long> {
        logger.info("Filtering warning alert ids that are resolved")
        return warningAlerts
            .filter { it.level == Level.RESOLVED }
            .map { it.alertId }
    }
}
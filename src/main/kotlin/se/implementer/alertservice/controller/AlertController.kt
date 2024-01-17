package se.implementer.alertservice.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import java.util.logging.Logger
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import se.implementer.alertservice.model.AlertDTO
import se.implementer.alertservice.repository.AlertRepository

@RestController
@RequestMapping("/v1/alerts")
class AlertController(val alertRepository: AlertRepository) {

    private val logger = Logger.getLogger(AlertController::class.java.name)

    @Operation(
        summary = "Fetches alert warnings from DB.",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Fetches alert warnings",
            ),
        ],
    )
    @GetMapping
    fun fetchAlerts(): List<AlertDTO> {
        logger.info("Fetching alerts from DB")
        return alertRepository
            .findAll()
            .toList()
            .map {
                alert -> AlertDTO.mapToAlertDto(alert)
            }
    }
}
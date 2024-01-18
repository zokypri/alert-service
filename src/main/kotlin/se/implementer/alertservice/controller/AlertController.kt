package se.implementer.alertservice.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import java.util.logging.Logger
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import se.implementer.alertservice.model.AlertDTO
import se.implementer.alertservice.service.AlertService

@RestController
@RequestMapping("/v1/alerts")
class AlertController(val alertService: AlertService) {

    private val logger = Logger.getLogger(AlertController::class.java.name)

    @Operation(
        summary = "Fetch warning alerts from DB.",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Fetch warning alerts",
            ),
        ],
    )
    @GetMapping("/all")
    fun fetchAllAlerts(): List<AlertDTO> {
        logger.info("Received request to fetch all warning alerts from DB")
        return alertService.fetchAlerts()
    }

    @Operation(
        summary = "Fetch not resolved warning alerts from DB.",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Fetch not resolved warning alerts",
            ),
        ],
    )
    @GetMapping("/not-resolved")
    fun fetchNotResolvedAlerts(): List<AlertDTO> {
        logger.info("Received request to fetch warning alerts that have not been resolved")
        return alertService.fetchNotResolvedAlerts()
    }

    @Operation(
        summary = "Fetch warning alerts from DB per alert id.",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Fetch warning alerts per alert id",
            ),
        ],
    )
    @GetMapping("/alertId")
    fun fetchAlertsById(@RequestParam alertId: Long): List<AlertDTO> {
        logger.info("Received request to fetch warning alerts from DB for alert id: $alertId")
        return alertService.fetchAlertsById(alertId)
    }
}
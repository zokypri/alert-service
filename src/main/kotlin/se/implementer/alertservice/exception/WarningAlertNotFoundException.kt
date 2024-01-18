package se.implementer.alertservice.exception

import java.lang.RuntimeException

class WarningAlertNotFoundException(alertId: Long) : RuntimeException("$WARNING_ALERT_ERROR $alertId") {

    companion object {
        const val WARNING_ALERT_ERROR = "No warning alerts found for alertId: "
    }
}


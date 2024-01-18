package se.implementer.alertservice.handler

import com.fasterxml.jackson.databind.exc.MismatchedInputException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.net.ConnectException
import java.util.logging.Logger
import se.implementer.alertservice.exception.WarningAlertNotFoundException

@ControllerAdvice
class GlobalExceptionHandler {

    private val logger = Logger.getLogger(GlobalExceptionHandler::class.java.name)
    @ExceptionHandler
    fun handleGlobalException(error: Throwable): ResponseEntity<ResponseError> {
        return createError(error, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler
    fun handleConnectException(error: ConnectException): ResponseEntity<ResponseError> {
        return createError(error, HttpStatus.SERVICE_UNAVAILABLE)
    }

    @ExceptionHandler
    fun handleWarningAlertNotFoundException(error: WarningAlertNotFoundException): ResponseEntity<ResponseError> {
        return createError(error, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler
    fun handleException(error: MismatchedInputException): ResponseEntity<ResponseError> {
        return createError(error, HttpStatus.BAD_REQUEST)
    }
    private fun createError(error: Throwable, responseCode: HttpStatus): ResponseEntity<ResponseError> {
        logger.severe("Error encountered with message: ${error.message}")
        return ResponseEntity(
            ResponseError(
                errorMessage = error.message,
                cause = error.cause
            ),
            null,
            responseCode
        )
    }
}

data class ResponseError(
    val errorMessage: String?,
    val cause: Throwable?,
)
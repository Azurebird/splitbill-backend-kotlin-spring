package com.splitbill.common

import com.splitbill.common.entity.response.ErrorResponse
import com.splitbill.common.exception.RestHttpException
import org.apache.commons.logging.LogFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler

/**
 * Class in charge of the exceptions handling
 */
@ControllerAdvice
class RestResponseEntityExceptionHandler : ResponseEntityExceptionHandler() {

    /**
     * Exception handler for all the exceptions which implemented the RestHttpException class
     *
     * @param e The caught exception
     * @return A response entity with a message and status code obtained from the caught exception
     */
    @ExceptionHandler(value = [RestHttpException::class])
    protected fun restExceptionHandler(e: RestHttpException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse(e.message, e.httpStatus.value()), e.httpStatus)
    }

    /**
     * Generic exception handler for not expected exceptions
     *
     * @param e The caught exception
     * @return A generic error message with 500 status error code
     */
    @ExceptionHandler(value = [Exception::class])
    protected fun exceptionHandler(e: Exception): ResponseEntity<ErrorResponse> {
        val message = "An unexpected exception occurred"
        logger.error(message, e)
        return ResponseEntity(ErrorResponse(message, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
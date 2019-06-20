package com.splitbill.common.entity

import com.splitbill.common.entity.response.ErrorResponse
import com.splitbill.common.exception.RestHttpException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
class RestResponseEntityExceptionHandler: ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [RestHttpException::class])
    protected fun exceptionHandler(e: RestHttpException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse(e.message, e.httpStatus.value()), e.httpStatus)
    }
}
package com.splitbill.entity

import com.splitbill.entity.response.BadRequestErrorResponse
import com.splitbill.entity.response.ErrorResponse
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import org.springframework.http.HttpStatus
import org.springframework.web.context.request.WebRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import java.lang.IllegalArgumentException


@ControllerAdvice
class RestResponseEntityExceptionHandler: ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [IllegalArgumentException::class])
    protected fun IllegalArgumentExceptionHandler(e: Exception): ResponseEntity<BadRequestErrorResponse> {
        return ResponseEntity(BadRequestErrorResponse(e.message), HttpStatus.BAD_REQUEST)
    }
}
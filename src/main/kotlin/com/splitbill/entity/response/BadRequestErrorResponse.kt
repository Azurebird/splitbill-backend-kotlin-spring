package com.splitbill.entity.response

import org.springframework.http.HttpStatus

class BadRequestErrorResponse(message: String?): ErrorResponse(message, HttpStatus.BAD_REQUEST.value(), 2000)

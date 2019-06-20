package com.splitbill.entity.response

import org.springframework.http.HttpStatus

class JwtErrorResponse(message: String): ErrorResponse(message, HttpStatus.BAD_REQUEST.value(), 2000)
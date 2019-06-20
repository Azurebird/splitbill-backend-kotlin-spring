package com.splitbill.common.entity.response

import org.springframework.http.HttpStatus

class JwtErrorResponse(message: String): ErrorResponse(message, HttpStatus.BAD_REQUEST.value())
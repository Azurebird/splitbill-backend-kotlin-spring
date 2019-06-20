package com.splitbill.common.exception

import org.springframework.http.HttpStatus

abstract class BadRequestException(message: String): RestHttpException(message, HttpStatus.BAD_REQUEST)
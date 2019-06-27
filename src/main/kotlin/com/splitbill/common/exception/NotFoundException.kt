package com.splitbill.common.exception

import org.springframework.http.HttpStatus

open class NotFoundException(message: String) : RestHttpException(message, HttpStatus.NOT_FOUND)
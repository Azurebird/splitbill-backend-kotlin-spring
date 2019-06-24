package com.splitbill.common.exception

import org.springframework.http.HttpStatus

class NotFoundException(message: String) : RestHttpException(message, HttpStatus.NOT_FOUND)
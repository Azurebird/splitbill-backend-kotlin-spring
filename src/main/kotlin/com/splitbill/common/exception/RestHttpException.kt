package com.splitbill.common.exception

import org.springframework.http.HttpStatus
import java.lang.RuntimeException

abstract class RestHttpException(message: String, val httpStatus: HttpStatus): RuntimeException(message)
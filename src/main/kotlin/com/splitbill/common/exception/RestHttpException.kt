package com.splitbill.common.exception

import org.springframework.http.HttpStatus
import java.lang.RuntimeException

/**
 * Abstract http error exception which defines the minimum required fields for and error response
 *
 * @property httpStatus The http error status code
 * @property errorCode A code related to the error thrown
 * @param message A message describing the error thrown
 */
abstract class RestHttpException(
    message: String,
    val httpStatus: HttpStatus,
    val errorCode: Int
) : RuntimeException(message)


package com.splitbill.common.exception

import org.springframework.http.HttpStatus

/**
 * Bad request error with a 400 http error code
 */
open class BadRequestException(
    message: String,
    errorCode: ExceptionCode
) : RestHttpException(message, HttpStatus.BAD_REQUEST, errorCode.code)

/**
 * Exception when the credentials are invalid
 */
class InvalidCredentialsException : BadRequestException("Invalid login and/or password", ExceptionCode.INVALID_CREDENTIALS)

/**
 * Exception when trying to create an user that already exists
 */
class UserAlreadyExistsException() : BadRequestException("That username already exists", ExceptionCode.USER_ALREADY_EXISTS)
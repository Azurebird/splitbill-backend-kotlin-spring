package com.splitbill.common.exception

import org.springframework.http.HttpStatus

/**
 * Not Found error with a 404 http error code
 */
open class NotFoundException(
    message: String,
    errorCode: ExceptionCode
) : RestHttpException(message, HttpStatus.NOT_FOUND, errorCode.code)

/**
 * Exception when trying to obtain a group that doesn't exists
 */
class GroupNotFoundException : NotFoundException("Group not found exception", ExceptionCode.GROUP_NOT_FOUND)

/**
 * Exception when trying to obtain a profile that doesn't exists
 */
class ProfileNotFoundException : NotFoundException("Profile not found exception", ExceptionCode.PROFILE_NOT_FOUND)
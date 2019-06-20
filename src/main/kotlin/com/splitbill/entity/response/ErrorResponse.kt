package com.splitbill.entity.response

/**
 * Response when an error occurs
 *
 * @property message
 * @property code
 * @property status
 */
abstract class ErrorResponse(
    val message: String?,
    val code: Int,
    val status: Int)

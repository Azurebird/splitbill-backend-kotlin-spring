package com.splitbill.common.entity.response

/**
 * Response when an error occurs
 *
 * @property message
 * @property code
 * @property status
 */
open class ErrorResponse(
    val message: String?,
    val code: Int
)

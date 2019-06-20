package com.splitbill.entity

/**
 * Response when an error occurs
 *
 * @property message
 * @property code
 * @property status
 */
abstract class ErrorResponse(
    private val message: String,
    private val code: Int,
    private val status: Int)

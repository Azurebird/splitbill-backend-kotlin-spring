package com.splitbill.common.exception

enum class ExceptionCode(val code: Int) {
    INVALID_CREDENTIALS(1001),
    USER_ALREADY_EXISTS(1002),

    GROUP_NOT_FOUND(2001),

    PROFILE_NOT_FOUND(3001),
}
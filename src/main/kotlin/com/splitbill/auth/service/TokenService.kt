package com.splitbill.auth.service

interface TokenService {

    /**
     * Creates a permanent token without expiration
     *
     * @param attributes
     * @return
     */
    fun permanentToken(attributes: Map<String, String>): String

    /**
     * Creates a token with which expires. The expiration time is given in the jwt.expiration-sec resource property
     *
     * @param attributes
     * @return
     */
    fun expiringToken(attributes: Map<String, String>): String

    /**
     * Checks the validity of the given credentials.
     *
     * @param token
     * @return attributes if verified
     */
    fun untrusted(token: String): Map<String, String>

    /**
     * Checks the validity of the given credentials.
     *
     * @param token
     * @return attributes if verified
     */
    fun verify(token: String): Map<String, String>
}
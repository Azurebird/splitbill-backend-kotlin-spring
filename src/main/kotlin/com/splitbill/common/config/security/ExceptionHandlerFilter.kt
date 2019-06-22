package com.splitbill.common.config.security

import com.google.gson.Gson
import com.splitbill.common.entity.response.JwtErrorResponse
import com.splitbill.common.exception.BadRequestException
import io.jsonwebtoken.JwtException
import org.springframework.http.HttpStatus
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ExceptionHandlerFilter : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        try {
            filterChain.doFilter(request, response)
        } catch (jwtException: JwtException) {
            logger.debug("A jwt exception occurred", jwtException)
            response.contentType = "application/json"
            response.status = HttpStatus.BAD_REQUEST.value()
            response.writer.write(Gson().toJson(JwtErrorResponse("Invalid token")))
        } catch (e: Exception) {
            logger.error(e)
        }
    }
}

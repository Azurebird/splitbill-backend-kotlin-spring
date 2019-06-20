package com.splitbill.common.config.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.web.util.matcher.OrRequestMatcher
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.http.HttpMethod
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Configuration
@EnableWebSecurity
class RestSecurityConfig(
    val tokenAuthenticationProvider: TokenAuthenticationProvider
): WebSecurityConfigurerAdapter() {

    /**
     * TODO("Pending to use this urls as the protected ones")
     */
    private val publicUrls = OrRequestMatcher(
            AntPathRequestMatcher("/profile/"),
            AntPathRequestMatcher("/login/")
    )

    /**
     * Protected urls patterns in which the authentication should be used
     */
    private val protectedUrls = OrRequestMatcher(
            AntPathRequestMatcher("/group/")
    )

    /**
     * Adds the custom TokenAuthenticationProvider to the authentication manager
     *
     * @param auth
     */
    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.authenticationProvider(tokenAuthenticationProvider)
    }

    /**
     * Configuration for the api rest security
     *
     * @param http
     */
    override fun configure(http: HttpSecurity) {
        http.csrf().disable()

        http
            .authenticationProvider(tokenAuthenticationProvider)
            .addFilterBefore(restAuthenticationFilter(), AnonymousAuthenticationFilter::class.java)
            .addFilterBefore(exceptionHandlerFilter(), TokenAuthenticationFilter::class.java)
            .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/profile/").permitAll()
                .antMatchers(HttpMethod.POST, "/login/").permitAll()
                .requestMatchers(protectedUrls).authenticated()
            .and()
            .csrf().disable()
            .formLogin().disable()
            .httpBasic().disable()
            .logout().disable()
    }

    /**
     * Creates a new TokenAuthenticationFilter to be used for the protected urls
     *
     * @return A new TokenAuthenticationFilter
     */
    @Bean
    fun restAuthenticationFilter(): TokenAuthenticationFilter {
        val filter = TokenAuthenticationFilter(protectedUrls)
        filter.setAuthenticationManager(authenticationManager())
        filter.setAuthenticationSuccessHandler(successHandler())
        return filter
    }

    /**
     * Creates a new TokenAuthenticationFilter to be used for the protected urls
     *
     * @return A new TokenAuthenticationFilter
     */
    @Bean
    fun exceptionHandlerFilter(): ExceptionHandlerFilter {
        return ExceptionHandlerFilter()
    }

    /**
     * Since the default behaviour of the success handler is to redirect, it's necessary to overwrite this strategy
     * with an empty body, this way it won't do anything
     *
     * @return An success handler with an empty redirect strategy
     */
    @Bean
    fun successHandler(): SimpleUrlAuthenticationSuccessHandler {
        val successHandler = SimpleUrlAuthenticationSuccessHandler()
        successHandler.setRedirectStrategy{ _: HttpServletRequest, _: HttpServletResponse, _: String -> }
        return successHandler
    }


    /**
     * Prevents any annotated filter to be added automatically by the spring IoC
     * TODO("Need to see this in action")
     *
     * @param filter
     * @return
     */
    @Bean
    fun disableAutoRegistration(filter: AbstractAuthenticationProcessingFilter): FilterRegistrationBean<*> {
        val registration = FilterRegistrationBean(filter)
        registration.isEnabled = false
        return registration
    }
}
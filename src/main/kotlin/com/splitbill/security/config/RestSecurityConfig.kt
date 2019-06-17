package com.splitbill.security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.web.util.matcher.OrRequestMatcher
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.boot.ansi.AnsiOutput.setEnabled
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter


@Configuration
@EnableWebSecurity
class RestSecurityConfig(
        val tokenAuthenticationProvider: TokenAuthenticationProvider
): WebSecurityConfigurerAdapter() {

    private val publicUrls = OrRequestMatcher(
            AntPathRequestMatcher("/public/**")
    )

    /**
     * Adds the custom TokenAuthenticationProvider to the authentication manager
     *
     * @param auth
     */
    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.authenticationProvider(tokenAuthenticationProvider)
    }

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()

        http
                .authenticationProvider(tokenAuthenticationProvider)
                .addFilterBefore(restAuthenticationFilter(), AnonymousAuthenticationFilter::class.java)
                .authorizeRequests()
                .anyRequest().permitAll()
    }

    @Bean
    fun restAuthenticationFilter(): TokenAuthenticationFilter {
        val filter = TokenAuthenticationFilter(publicUrls)
        filter.setAuthenticationManager(authenticationManager())
        //filter.setAuthenticationSuccessHandler(successHandler())
        return filter
    }

    /*@Bean
    fun successHandler(): SimpleUrlAuthenticationSuccessHandler {
        return SimpleUrlAuthenticationSuccessHandler()
    }*/

    @Bean
    fun disableAutoRegistration(filter: TokenAuthenticationFilter): FilterRegistrationBean<*> {
        val registration = FilterRegistrationBean(filter)
        registration.isEnabled = false
        return registration
    }
}
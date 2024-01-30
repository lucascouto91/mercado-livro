package com.mercadolivro.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.mercadolivro.controller.request.LoginRequest
import com.mercadolivro.repository.CustomerRepository
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException

class AuthenticationFilter(
    private val authenticationManager: AuthenticationManager,
    private val customerRepository: CustomerRepository,
    private val objectMapper: ObjectMapper
) : UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        try {
            val loginRequest = objectMapper.readValue(request.inputStream, LoginRequest::class.java)

            val userDetails = customerRepository.findByEmail(loginRequest.email) ?: throw BadCredentialsException("Invalid credentials")

            val authToken = UsernamePasswordAuthenticationToken(userDetails, loginRequest.password, emptyList())
            return authenticationManager.authenticate(authToken)
        } catch (ex: IOException) {
            throw BadCredentialsException("Invalid credentials", ex)
        }
    }
}

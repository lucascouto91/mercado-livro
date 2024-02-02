package com.mercadolivro.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.security.Key
import java.util.*

@Component
class JwtUtil {

    @Value("\${jwt.expiration}")
    private val expiration: Long? = null

    @Value("\${jwt.secret}")
    private val secret: String? = null

    fun generateToken(id: Int): String {
        val key : Key = Keys.hmacShaKeyFor(secret!!.toByteArray(StandardCharsets.UTF_8))
        return Jwts.builder()
            .subject(id.toString())
            .expiration(Date(System.currentTimeMillis() + expiration!!))
            .signWith(key)
            .compact()
    }

}
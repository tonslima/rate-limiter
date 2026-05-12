package com.ratelimiter

import RateLimiter
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.servlet.HandlerInterceptor
import java.util.UUID

class RateLimiterInterceptor(val limiter: RateLimiter) : HandlerInterceptor {

  override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
    val userId = request.getHeader("X-User-Id") ?: run {
      response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing X-User-Id header")
      return false
    }

    val endpoint = request.requestURI
    println(endpoint)
    val result = limiter.isAllowed(UUID.fromString(userId), endpoint)

    if (!result) {
      response.sendError(429, "Too Many Requests")
      return false
    }
    return true
  }
}

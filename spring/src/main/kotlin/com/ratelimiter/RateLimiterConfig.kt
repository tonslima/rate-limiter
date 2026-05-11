package com.ratelimiter

import RateLimiter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RateLimiterConfig {

  @Bean
  fun rateLimiter(): RateLimiter {
    return RateLimiter(3, 0.57)
  }

  @Bean
  fun rateLimiterInterceptor(): RateLimiterInterceptor {
    return RateLimiterInterceptor(rateLimiter())
  }
}
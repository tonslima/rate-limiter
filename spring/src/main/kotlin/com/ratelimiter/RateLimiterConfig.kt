package com.ratelimiter

import BucketStore
import RateLimiter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RateLimiterConfig {

  @Bean
  fun rateLimiter(store: BucketStore): RateLimiter {
    return RateLimiter(3, 0.57, store)
  }

  @Bean
  fun rateLimiterInterceptor(rateLimiter: RateLimiter): RateLimiterInterceptor {
    return RateLimiterInterceptor(rateLimiter)
  }
}
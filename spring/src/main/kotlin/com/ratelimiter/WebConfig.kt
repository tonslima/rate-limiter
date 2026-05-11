package com.ratelimiter

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(val interceptor: RateLimiterInterceptor): WebMvcConfigurer {

  override fun addInterceptors(registry: InterceptorRegistry) {
    registry.addInterceptor(interceptor)
  }
}

package com.ratelimiter

import BucketStore
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component

@Component
class RedisBucketStore(val template: RedisTemplate<String, String>) : BucketStore {

  override fun getTokens(key: String): Long {
    return template.opsForHash<String, String>()
      .get(key, "tokens")?.toLong() ?: 0L
  }

  override fun getLastRefill(key: String): Long {
    return template.opsForHash<String, String>()
      .get(key, "lastRefill")?.toLong() ?: 0L
  }

  override fun save(key: String, tokens: Long, lastRefill: Long) {
    val map = mapOf(
      "tokens" to tokens.toString(),
      "lastRefill" to lastRefill.toString()
    )

    template.opsForHash<String, String>()
      .putAll(key, map)
  }
}
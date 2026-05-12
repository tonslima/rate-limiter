import java.util.UUID
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

class RateLimiter(
  val capacity: Long,
  val refillRate: Double
) {
  init {
    require(capacity > 0) { "Capacity must be positive" }
    require(refillRate > 0 ) { "refillRate must be positive" }
  }

  private val userMap: ConcurrentMap<String, TokenBucket> = ConcurrentHashMap()

  fun isAllowed(userId: UUID, endpoint: String): Boolean {
    val key = "$userId::$endpoint"
    val tokenBucket = userMap.getOrPut(key) { TokenBucket(capacity, refillRate) }

    return tokenBucket.allowRequest()
  }
}
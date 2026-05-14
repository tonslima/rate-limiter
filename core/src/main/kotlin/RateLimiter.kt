import java.util.*

class RateLimiter(
  val capacity: Long,
  val refillRate: Double,
  val store: BucketStore
) {
  init {
    require(capacity > 0) { "Capacity must be positive" }
    require(refillRate > 0 ) { "refillRate must be positive" }
  }

  fun isAllowed(userId: UUID, endpoint: String): Boolean {
    val key = "$userId::$endpoint"
    val tokenBucket = TokenBucket(capacity, refillRate, key, store)

    return tokenBucket.allowRequest()
  }
}
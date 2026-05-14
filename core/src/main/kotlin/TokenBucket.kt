class TokenBucket(
  val capacity: Long,
  val refillRate: Double,
  val key: String,
  val store: BucketStore
) {

  @Synchronized
  fun allowRequest(): Boolean {
    var tokens = refillTokens()

    if (tokens > 0) {
      tokens -= 1
      store.save(key, tokens, System.currentTimeMillis())
      return true
    }
    return false
  }

  private fun refillTokens(): Long {
    var lastRefill = store.getLastRefill(key)
    var tokens = store.getTokens(key)

    val elapsedSeconds = (System.currentTimeMillis() - lastRefill) / 1000.0
    val tokensToAdd = elapsedSeconds * refillRate

    return minOf(capacity, tokens + tokensToAdd.toLong())
  }
}
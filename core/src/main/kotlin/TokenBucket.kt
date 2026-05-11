class TokenBucket(
  val capacity: Long,
  val refillRate: Double
) {
  private var tokens: Long = capacity
  private var lastRefill: Long = System.currentTimeMillis()

  @Synchronized
  fun allowRequest(): Boolean {
    refill()

    if (tokens > 0) {
      tokens -= 1
      return true
    }
    return false
  }

  private fun refill() {
    val elapsedSeconds = (System.currentTimeMillis() - lastRefill) / 1000.0
    val tokensToAdd = elapsedSeconds * refillRate

    tokens = minOf(capacity, tokens + tokensToAdd.toLong())
    lastRefill = System.currentTimeMillis()
  }
}

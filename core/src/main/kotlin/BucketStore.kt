interface BucketStore {
  fun getTokens(key: String): Long
  fun getLastRefill(key: String): Long
  fun save(key: String, tokens: Long, lastRefill: Long)
}
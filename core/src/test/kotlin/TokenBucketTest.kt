import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TokenBucketTest {

  @Test
  fun `should allow request when tokens are available`() {
    // arrange
    val bucket = TokenBucket(10, 1.0)

    // act
    val result = bucket.allowRequest()

    // assert
    assertTrue(result)
  }

  @Test
  fun `should deny request when tokens are not available`() {
    // arrange
    val bucket = TokenBucket(10, 1.0)

    // act
    repeat(10) { bucket.allowRequest() }
    val result = bucket.allowRequest()

    // assert
    assertFalse(result)
  }

  @Test
  fun `should allow request after refill`() {
    // arrange
    val bucket = TokenBucket(1, 1.0)

    // act
    bucket.allowRequest()
    Thread.sleep(1000)
    val result = bucket.allowRequest()

    // assert
    assertTrue(result)
  }
}
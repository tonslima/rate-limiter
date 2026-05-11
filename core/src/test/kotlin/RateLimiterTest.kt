import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.util.UUID
import kotlin.test.assertFailsWith

class RateLimiterTest {

  @Test
  fun `should allow request for user with available tokens`() {
    // arrange
    val limiter = RateLimiter(10, 1.0)

    // act
    val result = limiter.isAllowed(UUID.randomUUID())

    // result
    assertTrue(result)
  }

  @Test
  fun `should fail for capacity negative`() {
    // arrange and assert
    assertFailsWith<IllegalArgumentException> {
      RateLimiter(-1, 1.0)
    }
  }

  @Test
  fun `should fail for refillRate negative`() {
    // arrange and assert
    assertFailsWith<IllegalArgumentException> {
      RateLimiter(1, -1.0)
    }
  }

  @Test
  fun `should not share tokens between different users`() {
    // arrange
    val limiter = RateLimiter(1, 1.0)
    val userId = UUID.randomUUID()

    // act
    repeat(2) {
      limiter.isAllowed(userId)
    }
    val resultUser2 = limiter.isAllowed(UUID.randomUUID())

    // assert
    assertTrue(resultUser2)
  }
}
package implementations.java.rate_limiting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TokenBucketTest {

    private TokenBucket tokenBucket;

    @BeforeEach
    void setUp() {
        // Initialize the token bucket with a capacity of 10 tokens and a fill rate of 1 token per second
        tokenBucket = new TokenBucket(10, 1);
    }

    @Test
    void testAllowRequestWithinCapacity() {
        // Consume tokens within the bucket's capacity
        assertTrue(tokenBucket.allowRequest(5), "Request for 5 tokens should be allowed within capacity.");
        assertTrue(tokenBucket.allowRequest(3), "Request for 3 tokens should be allowed within remaining capacity.");
    }

    @Test
    void testDenyRequestExceedingCapacity() {
        // Request more tokens than the bucket's capacity, expecting denial
        assertFalse(tokenBucket.allowRequest(15), "Request for 15 tokens should be denied as it exceeds capacity.");
    }

    @Test
    void testAllowRequestWhenSufficientTokensAvailable() {
        // Consume tokens to reduce the bucket's available tokens
        assertTrue(tokenBucket.allowRequest(5), "Request for 5 tokens should be allowed.");
        assertFalse(tokenBucket.allowRequest(6), "Request for 6 tokens should be denied due to insufficient tokens.");
    }

    @Test
    void testTokenRefillOverTime() throws InterruptedException {
        // Consume all tokens
        assertTrue(tokenBucket.allowRequest(10), "Request for 10 tokens should be allowed to deplete the bucket.");
        
        // Wait long enough for tokens to refill (e.g., 3 seconds for 3 tokens at 1 token/sec)
        TimeUnit.SECONDS.sleep(3);

        // Now the bucket should have refilled some tokens, allowing smaller requests
        assertTrue(tokenBucket.allowRequest(2), "Request for 2 tokens should be allowed after refill.");
        assertTrue(tokenBucket.allowRequest(1), "Request for 1 token should be allowed after refill.");
    }

    @Test
    void testBucketNotExceedingCapacityOnRefill() throws InterruptedException {
        // Wait long enough to exceed the original capacity if tokens kept accumulating
        TimeUnit.SECONDS.sleep(15);  // Waiting 15 seconds should theoretically add 15 tokens

        // Attempt a request for the full capacity to confirm refill did not exceed maximum capacity
        assertTrue(tokenBucket.allowRequest(10), "Request for 10 tokens should be allowed after maximum refill.");
        assertFalse(tokenBucket.allowRequest(1), "Request for additional tokens should be denied as bucket is now empty.");
    }

    @Test
    void testConcurrentTokenConsumption() {
        // Simulate rapid token requests that might happen concurrently in real applications
        assertTrue(tokenBucket.allowRequest(1), "Request for 1 token should be allowed.");
        assertTrue(tokenBucket.allowRequest(1), "Request for 1 token should be allowed again.");
        assertTrue(tokenBucket.allowRequest(1), "Request for another 1 token should be allowed.");
        
        // Continue until tokens are exhausted
        for (int i = 0; i < 7; i++) {
            assertTrue(tokenBucket.allowRequest(1), "Request for 1 token should be allowed within capacity.");
        }

        // Bucket should now be empty, so any further requests should be denied
        assertFalse(tokenBucket.allowRequest(1), "Request for 1 token should be denied as bucket is empty.");
    }
}

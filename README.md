# rate-limiter

A study project implementing rate limiting from scratch in Kotlin, built in layers — starting with the core algorithm and evolving toward a Spring Boot integration.

The goal is to understand what actually happens under the hood before reaching for a library.

---

## Structure

```
rate-limiter/
├── core/      # Pure Kotlin, zero framework dependencies
└── spring/    # Spring Boot integration (in progress)
```

The `core` module has no knowledge of HTTP, Spring, or any web framework. It is a standalone implementation that can be plugged into any context.

---

## How it works

The algorithm used is **Token Bucket**.

Each user gets a bucket with a fixed capacity. Tokens are consumed on each request and replenished continuously over time based on a configurable rate. If the bucket is empty, the request is denied.

This is different from a Fixed Window counter, where all tokens reset at once at the end of a time window. Token Bucket refills proportionally to elapsed time, which avoids burst spikes at window boundaries and is generally fairer under real traffic patterns.

Two classes make up the core:

- `TokenBucket` — manages tokens for a single user. Thread-safe via `@Synchronized`.
- `RateLimiter` — holds a `ConcurrentHashMap` of buckets, one per user. Exposes a single method: `isAllowed(userId)`.

---

## Running the tests

```bash
./gradlew :core:test
```

---

## Roadmap

- [ ] Spring Boot module with `HandlerInterceptor`
- [ ] Rate limiting per user + endpoint
- [ ] Redis-backed state for distributed environments
- [ ] Support for multiple algorithms (Fixed Window, Sliding Window)
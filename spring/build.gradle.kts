plugins {
  kotlin("jvm")
  kotlin("plugin.spring") version "2.3.20"
  id("org.springframework.boot") version "3.4.5"
  id("io.spring.dependency-management") version "1.1.7"
}

repositories {
  mavenCentral()
}

dependencies {
  implementation(project(":core"))
  implementation("org.springframework.boot:spring-boot-starter-web")
}

springBoot {
  mainClass.set("com.ratelimiter.RateLimiterApplicationKt")
}
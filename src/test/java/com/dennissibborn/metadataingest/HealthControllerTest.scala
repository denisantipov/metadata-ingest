package com.dennissibborn.metadataingest

import org.junit.jupiter.api.Assertions.{assertEquals, assertTrue}
import org.junit.jupiter.api.Test

class HealthControllerTest extends TestBase {

  @Test
  def testGetHealthCheck(): Unit =
    val healthMessage = restTemplate.getForObject(s"http://localhost:$port/health_check", classOf[String])
    assertEquals(healthMessage, "OK")
}

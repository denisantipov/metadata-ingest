package com.dennissibborn.metadataingest

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class HealthControllerTest extends TestBase {

  @Test
  def testGetHealthCheck(): Unit =
    val healthMessage = restTemplate.getForObject(s"http://localhost:$port/health_check", classOf[Map[String, Any]])
    assertEquals(healthMessage("message").toString, "OK")
}

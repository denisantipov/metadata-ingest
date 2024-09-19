package com.dennissibborn.metadataingest

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class HealthController {
  @GetMapping(path = Array("/health_check"))
  def getHealthCheck: ResponseEntity[String] =
    ResponseEntity.ok().body("OK")
}

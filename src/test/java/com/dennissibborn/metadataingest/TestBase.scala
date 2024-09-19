package com.dennissibborn.metadataingest


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles

import scala.compiletime.uninitialized

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles(Array("local"))
class TestBase {

  @LocalServerPort
  val port = 0

  @Autowired
  var restTemplate: TestRestTemplate = uninitialized


}
 
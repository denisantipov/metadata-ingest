package com.dennissibborn.metadataingest

import org.junit.jupiter.api.Assertions.{assertEquals, assertTrue}
import org.junit.jupiter.api.Test

class FileListControllerTest extends TestBase {

  @Test
  def testGetFileList(): Unit =
    val fileList = restTemplate.getForObject(s"http://localhost:$port/api/list", classOf[List[Map[String, Any]]])
    assertTrue(fileList.size > 0)
    assertTrue(fileList.head("name").toString.size > 0)
}

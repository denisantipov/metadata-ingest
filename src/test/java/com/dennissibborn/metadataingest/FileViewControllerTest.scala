package com.dennissibborn.metadataingest

import org.junit.jupiter.api.Assertions.{assertEquals, assertTrue}
import org.junit.jupiter.api.Test

class FileViewControllerTest extends TestBase {

  @Test
  def testGetFileViewJson(): Unit =
    val fileList = restTemplate.getForObject(s"http://localhost:$port/api/list", classOf[List[Map[String, Any]]])
    val fileName = fileList.head("name")
    val data = restTemplate.getForObject(s"http://localhost:$port/api/view/json/$fileName", classOf[Map[String, Any]])
    assertTrue(data.size > 0)


  @Test
  def testGetFileView(): Unit =
    val fileList = restTemplate.getForObject(s"http://localhost:$port/api/list", classOf[List[Map[String, Any]]])
    val fileName = fileList.head("name")
    val data = restTemplate.getForObject(s"http://localhost:$port/api/view/$fileName", classOf[String])
    assertTrue(data.size > 0)

}

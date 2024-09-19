package com.dennissibborn.metadataingest.controller

import com.dennissibborn.metadataingest.service.FileService
import org.springframework.beans.factory.annotation.{Autowired, Value}
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RestController}

import scala.jdk.CollectionConverters.*

@RestController
@RequestMapping(path = Array("/api"))
class FileListController (@Autowired val fileService: FileService) {

  case class YamlFile(name:String, path: String, json: String)

  @GetMapping(path = Array("/list"))
  def getFileList(): java.util.List[YamlFile] = {
    val fileList = fileService.getS3FileNames.map(
      f => YamlFile(
        f,
        s"http://localhost:${fileService.serverPort}/api/view/${f}",
        s"http://localhost:${fileService.serverPort}/api/view/json/${f}"
      )
    )
    fileList.asJava
  }
}

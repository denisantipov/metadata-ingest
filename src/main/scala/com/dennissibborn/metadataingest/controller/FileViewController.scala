package com.dennissibborn.metadataingest.controller

import com.amazonaws.services.s3.AmazonS3
import com.dennissibborn.metadataingest.service.FileService
import org.springframework.beans.factory.annotation.{Autowired, Value}
import org.springframework.web.bind.annotation.{GetMapping, PathVariable, RequestMapping, RestController}


@RestController
@RequestMapping(path = Array("/api"))
class FileViewController(@Autowired val fileService: FileService) {
  val s3Client: AmazonS3 = fileService.getS3Client

  @GetMapping(path = Array("/view/json/{fileName}"))
  def getFileViewJson(@PathVariable fileName: String): Any = {
    val fileContents = fileService.getS3FileData(fileName)
    fileService.parseYaml(fileContents)
  }

  @GetMapping(path = Array("/view/{fileName}"))
  def getFileView(@PathVariable fileName: String): Any = {
    val fileContents = fileService.getS3FileData(fileName)
    fileContents
  }

}

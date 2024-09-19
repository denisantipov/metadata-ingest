package com.dennissibborn.metadataingest

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class MetadataIngestApplication

object MetadataIngestApplication extends App {
  SpringApplication.run(classOf[MetadataIngestApplication])
}
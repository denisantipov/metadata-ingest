package com.dennissibborn.metadataingest.service

import com.amazonaws.auth.{AWSStaticCredentialsProvider, BasicAWSCredentials}
import com.amazonaws.services.s3.{AmazonS3, AmazonS3ClientBuilder}
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker

import scala.jdk.CollectionConverters.*
import java.io.{BufferedReader, InputStreamReader}

@Service
class FileService {
  @Value("${server.port}")
  val serverPort:String = null

  @Value("${aws.region}")
  val awsRegion: String = null

  @Value("${aws.access-key}")
  val awsAccessKey: String = null

  @Value("${aws.secret-key}")
  val awsSecretKey: String = null

  @Value("${aws.s3.bucket}")
  val awsS3Bucket: String = null

  val s3Client: AmazonS3 = null

  def getS3Client: AmazonS3 = s3Client match
    case null => AmazonS3ClientBuilder.standard()
      .withCredentials(AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey)))
      .withRegion(awsRegion)
      .build()
    case _ => s3Client

  @CircuitBreaker(name = "fileService", fallbackMethod = "fallback")
  def getS3FileNames: List[String] = {
    val s3 = getS3Client
    val objectListing = s3.listObjects(awsS3Bucket)
    s3.listObjects(awsS3Bucket).getObjectSummaries.asScala.toList.map(f => f.getKey)
  }

  @CircuitBreaker(name = "fileService", fallbackMethod = "fallback")
  def getS3FileData(fileName: String): String = {
    val s3 = getS3Client
    val s3Obj = s3.getObject(awsS3Bucket, fileName)
    val in = s3Obj.getObjectContent
    val reader = new BufferedReader(new InputStreamReader(in))

    val data = LazyList.continually(reader.read()).takeWhile(_ != -1).map(_.toChar).mkString
    data
  }

  def parseYaml(yamlData:String) : Any = {
    val yamlReader = new ObjectMapper(new YAMLFactory)
    val yamlObj = yamlReader.readValue(yamlData, classOf[Any])
    yamlObj
  }

  def fallback(t: Throwable): ResponseEntity[Map[String, Any]] = {
    ResponseEntity.internalServerError().body(Map("message" -> "Request Timeout"))
  }

}

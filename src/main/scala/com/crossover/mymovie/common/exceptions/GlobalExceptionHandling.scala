package com.crossover.mymovie.common.exceptions

import java.util

import org.apache.log4j.Logger
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.{ControllerAdvice, ExceptionHandler}

import scala.collection.JavaConverters._

@ControllerAdvice
class GlobalExceptionHandling {

  val logger = Logger.getLogger(classOf[GlobalExceptionHandling])

  @ExceptionHandler
  def handleException(exception: MethodArgumentNotValidException): ResponseEntity[util.List[ErrorMessage]] = {
    logger.error(exception)
    val errorsMessages = getGlobalErrorsMessages(exception) ++ getFieldErrors(exception)
    ResponseEntity.badRequest().body(errorsMessages.asJava)
  }

  private def getGlobalErrorsMessages(exception: MethodArgumentNotValidException) = {
    val globalErrors = exception.getBindingResult.getGlobalErrors.asScala
    for (error <- globalErrors) yield ErrorMessage(error.getObjectName, error.getDefaultMessage)
  }

  private def getFieldErrors(exception: MethodArgumentNotValidException) = {
    val fieldErrors = exception.getBindingResult.getFieldErrors.asScala
    for (error <- fieldErrors) yield ErrorMessage(error.getField, error.getDefaultMessage)
  }
}

package com.example.common

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ControllerAdvice {
	@ExceptionHandler(Exception::class)
	fun exception(e: Exception): String {

		println("에러났쪄 > $e")
		throw e
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException::class)
	fun valid(e: Exception): String {

		println("에러났쪄 > $e")
		throw e
	}
}
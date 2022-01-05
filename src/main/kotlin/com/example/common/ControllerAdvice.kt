package com.example.common

import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ControllerAdvice {
	@ExceptionHandler(Exception::class)
	fun exception(e: Exception): String {

		println("에러났쪄 > $e")
		throw e
	}
}
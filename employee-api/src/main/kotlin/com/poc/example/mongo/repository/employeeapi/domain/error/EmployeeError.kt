package com.poc.example.mongo.repository.employeeapi.domain.error

import org.springframework.http.HttpStatus

enum class EmployeeError(val status: HttpStatus, val message: String) {
	
	INVALID_IDENTIFIER_PARAMETERS(HttpStatus.BAD_REQUEST, "invalid.identifier.parameters")
	
}
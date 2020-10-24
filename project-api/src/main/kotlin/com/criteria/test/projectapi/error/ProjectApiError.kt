package com.criteria.test.projectapi.error

import org.springframework.http.HttpStatus

enum class ProjectApiError(val status: HttpStatus, val message: String) {
	
	INVALID_IDENTIFIER_PARAMETERS(HttpStatus.BAD_REQUEST, "invalid.identifier.parameters")
	
}
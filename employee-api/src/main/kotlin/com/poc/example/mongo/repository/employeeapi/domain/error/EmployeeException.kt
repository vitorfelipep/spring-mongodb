package com.poc.example.mongo.repository.employeeapi.domain.error

import org.springframework.web.server.ResponseStatusException

class EmployeeException(
		employeeError: EmployeeError
) : ResponseStatusException(employeeError.status, employeeError.message)

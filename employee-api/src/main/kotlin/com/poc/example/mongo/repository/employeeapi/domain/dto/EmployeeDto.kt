package com.poc.example.mongo.repository.employeeapi.domain.dto

import com.poc.example.mongo.repository.employeeapi.domain.entity.Employee
import java.math.BigDecimal

class EmployeeDto(
		val id: String,
		val name: String,
		val description: String,
		val salary: BigDecimal,
		val band: String,
		val position: String
) {
	
	constructor(employee: Employee): this(
		id = employee.id ?: "",
		name = employee.name ?: "",
		description = employee.description ?: "",
		salary = employee.salary ?: BigDecimal.ZERO,
		band = employee.band ?: "",
		position = employee.position ?: ""
	)
	
	override fun toString(): String {
		return "EmployeeDto(name='$name', description='$description', salary=$salary, band='$band', position='$position')"
	}
}
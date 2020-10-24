package com.poc.example.mongo.repository.employeeapi.domain.entity

import com.poc.example.mongo.repository.employeeapi.domain.dto.EmployeeDto
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal

@Document("employee")
class Employee(
		@Id
		val id: String?,
		@Indexed
		val name: String?,
		val description: String?,
		val salary: BigDecimal?,
		val band: String?,
		val position: String?
) {
	
	constructor(employeeDto: EmployeeDto) : this(
			id = employeeDto.id,
			name = employeeDto.name,
			description = employeeDto.description,
			salary = employeeDto.salary,
			band = employeeDto.band,
			position = employeeDto.position
	)
	
	override fun toString(): String {
		return "Employee(id='$id', name='$name', description='$description', salary=$salary, band='$band', position='$position')"
	}
	
}
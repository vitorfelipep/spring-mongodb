package com.poc.example.mongo.repository.employeeapi.domain.service

import com.poc.example.mongo.repository.employeeapi.domain.dto.EmployeeDto
import com.poc.example.mongo.repository.employeeapi.domain.entity.Employee
import com.poc.example.mongo.repository.employeeapi.domain.error.EmployeeError
import com.poc.example.mongo.repository.employeeapi.domain.error.EmployeeException
import com.poc.example.mongo.repository.employeeapi.domain.repository.EmployeeRepository
import net.bytebuddy.matcher.ElementMatchers
import org.springframework.data.domain.*
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.*
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.math.BigDecimal

@Service
class EmployeeService(
	val employeeRepository: EmployeeRepository
) {
	
	fun listAllEmployees(): List<EmployeeDto> = employeeRepository.findAll().map { EmployeeDto(it) }
	
	fun save(employeeDto: EmployeeDto) = EmployeeDto(employeeRepository.save(Employee(employeeDto)))
	
	fun update(id: String, employeeDto: EmployeeDto): EmployeeDto? =
			if (employeeRepository.findById(id).isPresent) {
				EmployeeDto(employeeRepository.save(Employee(employeeDto)))
			} else {
				throw EmployeeException(EmployeeError.INVALID_IDENTIFIER_PARAMETERS)
			}
	
	fun delete(id: String) = employeeRepository.deleteById(id)
	
	fun findAllEmployees(
			search: String?,
			position: String?,
			page: Int,
			size: Int,
			sort: String,
			direction: String
	): Page<EmployeeDto>? {
		val pages: Pageable = PageRequest.of(page, size, Sort.Direction.valueOf(direction), sort)
		val matcher = ExampleMatcher.matching()
				.withMatcher("name", contains().ignoreCase())
				.withMatcher( "position", contains().ignoreCase())
				.withIgnorePaths("id", "description", "salary", "band")
		
		val example: Example<Employee> = Example.of(
				createEmployeeToExample(search, position),
				matcher
		)
		
		return selectWhichSearchToUse(search, position, example, pages)
	}
	
	private fun selectWhichSearchToUse(
			search: String?,
			position: String?,
			example: Example<Employee>,
			pages: Pageable
	) = if (search != null || position != null) {
			employeeRepository.findAll(example, pages).map { EmployeeDto(it) }
		} else {
			employeeRepository.findAll(pages).map { EmployeeDto(it) }
		}
	
	private fun createEmployeeToExample(search: String?, position: String?) =
			Employee("id", search, "", BigDecimal.ZERO, "", position)
	
	
}
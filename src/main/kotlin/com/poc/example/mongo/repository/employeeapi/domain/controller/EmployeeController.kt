package com.poc.example.mongo.repository.employeeapi.domain.controller

import com.poc.example.mongo.repository.employeeapi.domain.dto.EmployeeDto
import com.poc.example.mongo.repository.employeeapi.domain.service.EmployeeService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import springfox.documentation.annotations.ApiIgnore
import java.security.Principal

@RestController
@RequestMapping("/v1/employees")
class EmployeeController(
		val employeeService: EmployeeService
) {
	
	@GetMapping(produces = ["application/json"])
	@ApiOperation(value = "Busca todos os empregados cadastrados no sistema", consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponses(value = [
		ApiResponse(code = 200, message = "Retorna uma lista de usuários", response = List::class, responseContainer = "List")
	])
	fun listAllEmployees(): List<EmployeeDto> = employeeService.listAllEmployees()
	
	@GetMapping("/by/name/{search}/position/{position}", produces = ["application/json"])
	@ApiOperation(value = "Busca todos os empregados cadastrados no sistema com filtro, ordenação e paginação", consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponses(value = [
		ApiResponse(code = 200, message = "Retorna uma lista de empregados sendo com paginação", response = Page::class, responseContainer = "List"),
		ApiResponse(code = 404, message = "user.not.found")
	])
	fun findAllUsersWithPagination(
			@RequestParam(value = "search", required = false) search: String?,
			@RequestParam(value = "position", required = false) position: String?,
			@RequestParam(value = "page", defaultValue = "0") page: Int,
			@RequestParam(value = "size", defaultValue = "5") size: Int,
			@RequestParam(value = "sort", defaultValue = "id") sort: String,
			@RequestParam(value = "direction", defaultValue = "ASC") direction: String
	): Page<EmployeeDto>? = employeeService.findAllEmployees(search, position, page, size, sort, direction)
	
	@PostMapping(produces = ["application/json"])
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Cria um empregado", consumes = "application/json")
	@ApiResponses(value = [
		ApiResponse(code = 201, message = "Retorna o usuário salvo", response = EmployeeDto::class)
	])
	fun save(@RequestBody employeeDto: EmployeeDto): EmployeeDto? = employeeService.save(employeeDto)
	
	@PutMapping(produces = ["application/json"])
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Atualiza um empregado da base de dados", consumes = "application/json")
	@ApiResponses(value = [
		ApiResponse(code = 200, message = "Retorna o usuário atualizado", response = EmployeeDto::class)
	])
	fun update(
			@RequestBody employeeDto: EmployeeDto,
			@PathVariable id: String
	): EmployeeDto? = employeeService.update(id, employeeDto)
	
	@DeleteMapping("/{id}", produces = ["application/json"])
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Remove um empregado da base de dados de forma fisica", consumes = "application/json")
	@ApiResponses(value = [
		ApiResponse(code = 200, message = "Retorna o campus removido", response = EmployeeDto::class),
		ApiResponse(code = 404, message = "employee.not.found")
	])
	fun deleteCampus(
		@PathVariable id: String
	) = employeeService.delete(id)
	
}
package com.criteria.test.projectapi.domain.controller

import com.criteria.test.projectapi.domain.dto.ProjectDTO
import com.criteria.test.projectapi.domain.service.ProjectService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@Api(value = "Projects", description = "Disponibiliza os projetos internos")
@RestController
@RequestMapping("/v1/projects")
class ProjectController(
		val projectService: ProjectService
) {
	
	@GetMapping(produces = ["application/json"])
	@ApiOperation(value = "Busca todos os projetos cadastrados no sistema", consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponses(value = [
		ApiResponse(code = 200, message = "Retorna lista de projetos", response = List::class, responseContainer = "List")
	])
	fun listAllProjects(): List<ProjectDTO> = projectService.findAll()
	
	
	@GetMapping("/search/{search}/description/{description}", produces = ["application/json"])
	@ApiOperation(value = "Busca todos os projetos cadastrados no sistema com filtro, ordenação e paginação", consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponses(value = [
		ApiResponse(code = 200, message = "Retorna uma lista de projetos com paginação", response = Page::class, responseContainer = "List"),
		ApiResponse(code = 404, message = "project.not.found")
	])
	fun findAllUsersWithPagination(
			@RequestParam(value = "search", required = false) search: String?,
			@RequestParam(value = "description", required = false) description: String?,
			@RequestParam(value = "page", defaultValue = "0") page: Int,
			@RequestParam(value = "size", defaultValue = "5") size: Int,
			@RequestParam(value = "sort", defaultValue = "id") sort: String,
			@RequestParam(value = "direction", defaultValue = "ASC") direction: String
	): Page<ProjectDTO>? = projectService.findAllByFiltering(search, description, page, size, sort, direction)
	
	@PostMapping(produces = ["application/json"])
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Cria um novo usuário", consumes = "application/json")
	@ApiResponses(value = [
		ApiResponse(code = 201, message = "Retorna o usuário salvo", response = ProjectDTO::class)
	])
	fun save(@RequestBody projectDTO: ProjectDTO): ProjectDTO? = projectService.save(projectDTO)

}
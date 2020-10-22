package com.mongodb.paginacao.teste.testemongopaginacaofiltroapi.api

import com.mongodb.paginacao.teste.testemongopaginacaofiltroapi.entity.User
import com.mongodb.paginacao.teste.testemongopaginacaofiltroapi.service.UserService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/users")
@Api(value = "User", description = "Disponibiliza usuários do sistema")
class UserController(
		val userService: UserService
) {
	
	@GetMapping(produces = ["application/json"])
	@ApiOperation(value = "Busca todos os usuarios cadastrados no sistema sem paginação", consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponses(value = [
		ApiResponse(code = 200, message = "Retorna uma lista de usuários", response = List::class, responseContainer = "List"),
		ApiResponse(code = 404, message = "user.not.found")
	])
	fun findAll(): List<User> = userService.findAll()
	
	@GetMapping("/by/name/{search}/age/{age}", produces = ["application/json"])
	@ApiOperation(value = "Busca todos os usuarios cadastrados no sistema com filtro, ordenação e paginação utilizando query dsl", consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponses(value = [
		ApiResponse(code = 200, message = "Retorna uma lista de usuários sendo com paginação", response = Page::class, responseContainer = "List"),
		ApiResponse(code = 404, message = "user.not.found")
	])
	fun findAllUsersWithPagination(
		@RequestParam(value = "search", required = false) search: String?,
		@RequestParam(value = "age", required = false) age: Int?,
		@RequestParam(value = "page", defaultValue = "0") page: Int,
		@RequestParam(value = "size", defaultValue = "5") size: Int,
		@RequestParam(value = "sort", defaultValue = "id") sort: String,
		@RequestParam(value = "direction", defaultValue = "ASC") direction: String
	): Page<User> = userService.findAllUsers(search, age, page, size, sort, direction)
	
	@GetMapping("/name/{search}", produces = ["application/json"])
	@ApiOperation(value = "Busca todos os usuarios cadastrados no sistema " +
			"sendo filtrado por nome ou id utilizando named query do Spring data " +
			"repository retornando uma paginação", consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponses(value = [
		ApiResponse(code = 200, message = "Retorna uma lista de usuários filtrada por nome ou id", response = Page::class, responseContainer = "List"),
		ApiResponse(code = 404, message = "user.not.found")
	])
	fun findAllWithNamedQuerySpringDataJPA(
		@RequestParam(value = "search", required = false) search: String?,
		@RequestParam(value = "page", defaultValue = "0") page: Int,
		@RequestParam(value = "size", defaultValue = "5") size: Int,
		@RequestParam(value = "sort", defaultValue = "id") sort: String,
		@RequestParam(value = "direction", defaultValue = "ASC") direction: String
	): Page<User>? = userService.finByNameOrId(search, page, size, sort, direction)
	
	@PostMapping(produces = ["application/json"])
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Cria um novo usuário", consumes = "application/json")
	@ApiResponses(value = [
		ApiResponse(code = 201, message = "Retorna o usuário salvo", response = User::class),
	])
	fun save(@RequestBody user: User): User? = userService.save(user)
	
}
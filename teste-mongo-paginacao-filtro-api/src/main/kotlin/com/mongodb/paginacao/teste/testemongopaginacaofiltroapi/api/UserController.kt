package com.mongodb.paginacao.teste.testemongopaginacaofiltroapi.api

import com.mongodb.paginacao.teste.testemongopaginacaofiltroapi.entity.User
import com.mongodb.paginacao.teste.testemongopaginacaofiltroapi.service.UserService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/users")
class UserController(
		val userService: UserService
) {
	@GetMapping
	fun userList(): ResponseEntity<List<User>>  = ResponseEntity.ok().body(userService.findAll())
	
	@GetMapping("/{page}/{count}")
	fun userList(@PathVariable page: Int, @PathVariable count: Int): ResponseEntity<Page<User>> =
			ResponseEntity.ok().body(userService.findAllUsers(count, page))
	
	@GetMapping("/{name}/name")
	fun userList(@PathVariable name: String): ResponseEntity<List<User>> = ResponseEntity.ok().body(userService.finByName(name))
	
	@PostMapping
	fun save(@RequestBody user: User): ResponseEntity<User> = ResponseEntity.ok().body(userService.save(user))
}
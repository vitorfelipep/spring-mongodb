package com.mongodb.paginacao.teste.testemongopaginacaofiltroapi.service

import com.mongodb.paginacao.teste.testemongopaginacaofiltroapi.entity.User
import com.mongodb.paginacao.teste.testemongopaginacaofiltroapi.repository.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class UserService(
		val userRepository: UserRepository
) {
	
	fun findAll(): List<User> = userRepository.findAll()
	
	fun findAllUsers(count: Int, page: Int): Page<User> {
		val pages: Pageable = PageRequest.of(page, count)
		return userRepository.findAll(pages)
	}
	
	fun finByName(name: String): List<User> = userRepository.findByNameLikeIgnoreCase(name)
	
	fun save(user: User): User? = userRepository.save(user)
	
}
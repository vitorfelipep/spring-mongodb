package com.mongodb.paginacao.teste.testemongopaginacaofiltroapi.service


import com.mongodb.paginacao.teste.testemongopaginacaofiltroapi.entity.QUser
import com.mongodb.paginacao.teste.testemongopaginacaofiltroapi.entity.User
import com.mongodb.paginacao.teste.testemongopaginacaofiltroapi.repository.UserRepository
import com.querydsl.core.BooleanBuilder
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class UserService(
		val userRepository: UserRepository
) {
	
	fun findAll(): List<User> = userRepository.findAll()
	
	fun findAllUsers(
			search: String?,
			age: Int?,
			page: Int,
			size: Int,
			sort: String,
			dir: String
	): Page<User> {
		val pages: Pageable = PageRequest.of(page, size, Sort.Direction.valueOf(dir), sort)
		val qUser = QUser("User")
		val booleanBuilder = BooleanBuilder()
		
		if (search != null) {
			booleanBuilder.and(qUser.name.containsIgnoreCase(search)).or(qUser.id.eq(search))
		}
		
		if (age != null) {
			booleanBuilder.and(qUser.age.eq(age))
		}
		
		return userRepository.findAll(booleanBuilder, pages)
	}
	
	fun save(user: User): User? = userRepository.save(user)
	
	fun finByNameOrId(search: String?, page: Int, size: Int, sort: String, direction: String): Page<User>? =
			search?.let { userRepository.findByNameLikeIgnoreCaseOrId(it, it, PageRequest.of(page, size, Sort.Direction.valueOf(direction), sort)) }
	
}
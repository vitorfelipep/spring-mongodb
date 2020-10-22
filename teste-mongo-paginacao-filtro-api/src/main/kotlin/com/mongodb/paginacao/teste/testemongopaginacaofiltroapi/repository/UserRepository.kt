package com.mongodb.paginacao.teste.testemongopaginacaofiltroapi.repository

import com.mongodb.paginacao.teste.testemongopaginacaofiltroapi.entity.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor

interface UserRepository : MongoRepository<User, String>, QuerydslPredicateExecutor<User> {
	
	fun findByNameLikeIgnoreCaseOrId(name: String, id: String, page: Pageable): Page<User>
	
}
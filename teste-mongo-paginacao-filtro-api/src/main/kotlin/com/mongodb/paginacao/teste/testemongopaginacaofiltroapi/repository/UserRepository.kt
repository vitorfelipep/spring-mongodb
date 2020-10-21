package com.mongodb.paginacao.teste.testemongopaginacaofiltroapi.repository

import com.mongodb.paginacao.teste.testemongopaginacaofiltroapi.entity.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, String>{
	
	fun findByNameLikeIgnoreCase(name: String): List<User>
	
}
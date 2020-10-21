package com.mongodb.paginacao.teste.testemongopaginacaofiltroapi.entity

import org.springframework.data.mongodb.core.mapping.Document

@Document
data class User(
		val id: String,
		val name: String,
		val age: Int,
		val email: String
)
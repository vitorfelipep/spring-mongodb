package com.mongodb.paginacao.teste.testemongopaginacaofiltroapi.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class User(
		@Id
		var id: String,
		var name: String,
		var age: Int,
		var email: String
)
package com.mongodb.paginacao.teste.testemongopaginacaofiltroapi.entity

import com.querydsl.core.types.Path
import com.querydsl.core.types.PathMetadata
import com.querydsl.core.types.PathMetadataFactory
import com.querydsl.core.types.dsl.EntityPathBase
import java.io.Serializable
import javax.annotation.processing.Generated

@Generated("com.querydsl.codegen.EntitySerializer")
class QUser : EntityPathBase<User>, Serializable {
	
	@JvmField
	var age = createNumber("age", Int::class.java)
	
	@JvmField
	var id = createString("id")
	
	@JvmField
	var name = createString("name")
	
	@JvmField
	var email = createString("email")
	
	constructor(variable: String?) : super(User::class.java, PathMetadataFactory.forVariable(variable)) {}
	constructor(path: Path<out User?>) : super(path.type, path.metadata) {}
	constructor(metadata: PathMetadata?) : super(User::class.java, metadata) {}
	
	companion object {
		
		@JvmField
		val user = QUser("user")
	}
}

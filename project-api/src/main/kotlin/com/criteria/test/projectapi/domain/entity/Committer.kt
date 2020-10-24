package com.criteria.test.projectapi.domain.entity

import com.criteria.test.projectapi.domain.dto.CommiterDTO
import com.criteria.test.projectapi.extensions.kotlin.generatedUIID
import java.util.*

data class Committer(
	val id: String?,
	val name: String
) {
	
	constructor(commiterDTO: CommiterDTO): this(
		id = commiterDTO.id?.let {
			if(it.isNotBlank()) it else it.generatedUIID()
		} ?: String().generatedUIID(),
		name = commiterDTO.name
	)
	
	override fun toString(): String {
		return "Commiter(id='$id', name='$name')"
	}
	
}

package com.criteria.test.projectapi.domain.entity

import com.criteria.test.projectapi.domain.dto.InfoDTO
import com.criteria.test.projectapi.extensions.kotlin.generatedUIID

class Info(
	val id: String?,
	val description: String?
) {
	
	constructor(infoDTO: InfoDTO): this(
			id = infoDTO.id?.let {
				if(it.isNotBlank()) it else it.generatedUIID()
			} ?: String().generatedUIID(),
			description = infoDTO.description
	)
	
	override fun toString(): String {
		return "Info(id='$id', description='$description')"
	}
}

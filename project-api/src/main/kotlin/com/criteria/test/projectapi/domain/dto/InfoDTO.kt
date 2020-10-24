package com.criteria.test.projectapi.domain.dto

import com.criteria.test.projectapi.domain.entity.Info

class InfoDTO(
		val id: String?,
		val description: String?
) {
	
	constructor(info: Info): this(
			id = info.id,
			description = info.description
	)
	
	override fun toString(): String {
		return "InfoDTO(id='$id', description=$description)"
	}
	
}
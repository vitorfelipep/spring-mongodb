package com.criteria.test.projectapi.domain.dto

import com.criteria.test.projectapi.domain.entity.Committer

open class CommiterDTO(
	val id: String?,
	val name: String
) {
	
	constructor(committer: Committer): this(
		id = committer.id,
		name = committer.name
	)
	
	override fun toString(): String {
		return "CommiterDTO(id='$id', name='$name')"
	}
	
}
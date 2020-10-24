package com.criteria.test.projectapi.domain.dto

import com.criteria.test.projectapi.domain.entity.Version

class VersionDTO(
		val id: String?,
		val name: String,
		val committers: List<CommiterDTO>?
) {
	
	constructor(version: Version?): this (
			id = version?.id,
			name = version?.name ?: "0.0.1-SNAPSHOT",
			committers = version?.committers?.map { CommiterDTO(it) }
	)
	
	override fun toString(): String {
		return "VersionCollectionDTO(id='$id', name='$name', committers=$committers)"
	}
	
}
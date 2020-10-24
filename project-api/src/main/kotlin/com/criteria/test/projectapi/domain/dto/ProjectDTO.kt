package com.criteria.test.projectapi.domain.dto

import com.criteria.test.projectapi.domain.entity.Project

class ProjectDTO(
		val id: String?,
		val name: String,
		val versions: List<VersionDTO>?,
		val info: InfoDTO?
) {
	
	constructor(project: Project): this(
			id = project.id,
			name = project.name,
			versions = project.versions?.map { VersionDTO(it) },
			info = project.info?.let { InfoDTO(it) }
	)
	
	override fun toString(): String {
		return "ProjectDTO(id='$id', name='$name', versions=$versions, info=$info)"
	}
	
}
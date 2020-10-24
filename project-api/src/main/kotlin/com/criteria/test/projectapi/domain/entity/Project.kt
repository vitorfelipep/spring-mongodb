package com.criteria.test.projectapi.domain.entity

import com.criteria.test.projectapi.domain.dto.ProjectDTO
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "projects")
class Project(
		@Id
		val id: String?,
		@Indexed
		val name: String,
		@DBRef
		val versions: List<Version>?,
		val info: Info?
) {
	
	constructor(projectDTO: ProjectDTO): this(
			id = projectDTO.id,
			name = projectDTO.name,
			versions = projectDTO.versions?.map { Version(it) },
			info = projectDTO.info?.let { Info(it) }
	)
	
	override fun toString(): String {
		return "Project(id='$id', name='$name', versions=$versions, info=$info)"
	}
}
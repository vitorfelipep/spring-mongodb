package com.criteria.test.projectapi.domain.entity

import com.criteria.test.projectapi.domain.dto.VersionDTO
import com.criteria.test.projectapi.extensions.kotlin.generatedUIID
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Version(
		@Id
		var id: String?,
		@Indexed
		val name: String,
		val committers: List<Committer>?
) {
	
	constructor(versionDTO: VersionDTO?): this (
			id = versionDTO?.id?.let {
				if(it.isNotBlank()) it else it.generatedUIID()
			} ?: String().generatedUIID(),
			name = versionDTO?.name ?: "0.0.1-SNAPSHOT",
			committers = versionDTO?.committers?.map { Committer(it) }
	)
	
	override fun toString(): String {
		return "VersionCollection(id='$id', name='$name', committers=$committers)"
	}
}

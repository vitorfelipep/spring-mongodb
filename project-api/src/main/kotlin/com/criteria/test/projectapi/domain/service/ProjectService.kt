package com.criteria.test.projectapi.domain.service

import com.criteria.test.projectapi.domain.dto.ProjectDTO
import com.criteria.test.projectapi.domain.entity.Project
import com.criteria.test.projectapi.domain.repository.ProjectRepository
import com.criteria.test.projectapi.domain.repository.VersionRepository
import org.springframework.data.domain.*
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.repository.support.PageableExecutionUtils
import org.springframework.stereotype.Service

@Service
class ProjectService(
		val projectRepository: ProjectRepository,
		val versionRepository: VersionRepository,
		val mongoTemplate: MongoTemplate
) {
	
	fun save(projectDTO: ProjectDTO): ProjectDTO  {
		val project = Project(projectDTO)
		project.versions?.forEach { versionRepository.save(it) }
		return ProjectDTO(projectRepository.save(project))
	}
	
	fun findAll(): List<ProjectDTO> = projectRepository.findAll().map { ProjectDTO(it) }
	
	fun findAllByFiltering(
			search: String?,
			description: String?,
			page: Int,
			size: Int,
			sort: String,
			direction: String
	): Page<ProjectDTO> {
		val pages: Pageable = PageRequest.of(page, size, Sort.Direction.valueOf(direction), sort)
		
		if (search != null || description != null) {
			val query = createQuery(search, description, pages)
			val projects = mongoTemplate.find(query, Project::class.java)
			val projectsDTO = projects.map { ProjectDTO(it) }
			
			return PageImpl<ProjectDTO>(projectsDTO, pages, count(query))
		}
		
		return projectRepository.findAll(pages).map { ProjectDTO(it) }
	}
	
	private fun createQuery(
			search: String?,
			description: String?,
			pages: Pageable
	): Query {
		val query = Query()
		
		search?.let {
			val criteriaName = Criteria.where("name").regex(".*" + it.toLowerCase() + ".*", "i")
			val criteriaId = Criteria.where("id").`is`(it)
			query.addCriteria(Criteria().orOperator(criteriaName, criteriaId))
		}
		
		description?.let {
			query.addCriteria(Criteria.where("info.description").regex(".*" + it.toLowerCase() + ".*", "i"))
		}
		
		query.with(pages)
		return query
	}
	
	private fun count(query: Query): Long = mongoTemplate.count(query, Project::class.java)
	
}
package com.criteria.test.projectapi.domain.repository

import com.criteria.test.projectapi.domain.entity.Project
import org.springframework.data.mongodb.repository.MongoRepository

interface ProjectRepository : MongoRepository<Project, String>
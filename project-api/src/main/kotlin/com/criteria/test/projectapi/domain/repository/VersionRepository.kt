package com.criteria.test.projectapi.domain.repository

import com.criteria.test.projectapi.domain.entity.Version
import org.springframework.data.mongodb.repository.MongoRepository

interface VersionRepository : MongoRepository<Version, String>
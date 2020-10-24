package com.criteria.test.projectapi.domain.repository

import com.criteria.test.projectapi.domain.entity.Info
import org.springframework.data.mongodb.repository.MongoRepository

interface InfoRepository : MongoRepository<Info, String>
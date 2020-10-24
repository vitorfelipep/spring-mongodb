package com.criteria.test.projectapi.error

import org.springframework.web.server.ResponseStatusException

class ProjectErrorException(
		val projectApiError: ProjectApiError
) : ResponseStatusException(projectApiError.status, projectApiError.message)

package com.criteria.test.projectapi.extensions.kotlin

import java.util.*

fun String.generatedUIID(): String {
	return UUID.randomUUID().toString()
}
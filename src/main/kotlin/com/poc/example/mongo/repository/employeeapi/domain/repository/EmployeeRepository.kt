package com.poc.example.mongo.repository.employeeapi.domain.repository

import com.poc.example.mongo.repository.employeeapi.domain.entity.Employee
import org.springframework.data.mongodb.repository.MongoRepository

interface EmployeeRepository : MongoRepository<Employee, String>
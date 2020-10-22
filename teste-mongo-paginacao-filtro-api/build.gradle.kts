import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.3.4.RELEASE"
	id("io.spring.dependency-management") version "1.0.10.RELEASE"
	kotlin("jvm") version "1.3.72"
	kotlin("plugin.spring") version "1.3.72"
	id("com.ewerk.gradle.plugins.querydsl") version "1.0.10"
	kotlin("kapt") version "1.4.10"
}


group = "com.mongodb.paginacao.teste"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}


dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("com.querydsl:querydsl-mongodb:4.2.2")
	implementation("com.querydsl:querydsl-apt:4.2.2")
	implementation("io.springfox:springfox-bean-validators:2.9.2")
	implementation("io.springfox:springfox-swagger-ui:2.9.2")
	implementation("io.springfox:springfox-swagger2:2.9.2")
	implementation("io.swagger:swagger-annotations:1.6.0")
	implementation("io.swagger:swagger-models:1.6.0")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	implementation("com.google.dagger:dagger-compiler:2.0")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

kapt {
	arguments {
		arg("key", "value")
	}
}

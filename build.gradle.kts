val kotlinVersion: String by project
val logbackVersion: String by project
val ktorVersion: String by project
val exposedVersion: String by project

project.ext.set("development", true)

plugins {
  kotlin("jvm") version "2.0.21"
  id("io.ktor.plugin") version "3.0.0"
}

group = "marketplace"
version = "0.0.1"

application {
  mainClass.set("io.ktor.server.netty.EngineMain")

  val isDevelopment: Boolean = project.ext.has("development")
  applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

tasks.register("build-db") {
  group = "build"
  description = "Builds the database"
  dependsOn("installDist")
  // we should create the db here and install all the migrations
}

repositories {
  mavenCentral()
  maven("https://maven.pkg.github.com/bitwarden/sdk") {
    credentials {
      username = System.getenv("GITHUB_USERNAME")
      password = System.getenv("GITHUB_REPO_TOKEN")
    }
  }
}

dependencies {
  implementation("io.ktor:ktor-server-core-jvm")
  implementation("io.ktor:ktor-server-auth-jvm")
  implementation("io.ktor:ktor-server-auth-jwt-jvm")
  implementation("io.ktor:ktor-client-core-jvm")
  implementation("io.ktor:ktor-client-apache-jvm")
  implementation("io.ktor:ktor-server-netty-jvm")
  implementation("com.bitwarden:sdk-secrets:1.0.1")
  implementation("io.github.cdimascio:dotenv-kotlin:6.4.2")

  implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
  implementation("org.jetbrains.exposed:exposed-crypt:$exposedVersion")
  implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
  implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
  implementation("org.jetbrains.exposed:exposed-kotlin-datetime:$exposedVersion")
  implementation("org.jetbrains.exposed:exposed-json:$exposedVersion")
  implementation("org.jetbrains.exposed:exposed-money:$exposedVersion")
//  implementation("org.jetbrains.exposed:exposed-migration:$exposedVersion")
  implementation("org.xerial:sqlite-jdbc:3.44.1.0")
  implementation("org.mindrot:jbcrypt:0.4")

  implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")
  implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
  implementation("ch.qos.logback:logback-classic:$logbackVersion")
  testImplementation("io.ktor:ktor-server-test-host-jvm")
  testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")
}



import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version "1.3.31"
}

group = "dev.castive"
version = "0.1.0"

repositories {
	jcenter()
	maven(url = "https://jitpack.io")
}

dependencies {
	implementation(kotlin("stdlib-jdk8"))

	implementation("com.github.djcass44:log2:3.3")
	implementation("com.google.code.gson:gson:2.8.5")

	testImplementation("org.junit.jupiter:junit-jupiter-api:5.2.0")
	testImplementation("org.junit.jupiter:junit-jupiter-params:5.2.0")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.2.0")
}

tasks.withType<KotlinCompile>().all {
	kotlinOptions.jvmTarget = "11"
}
tasks.withType<Test> {
	useJUnitPlatform()
}
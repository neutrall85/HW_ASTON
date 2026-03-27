val junitVersion = "5.11.0"
val junitApiVersion = "5.11.0"
val assertjCoreVersion = "3.24.2"
val junitEngineVersion = "5.9.2"


plugins {
    id("java")
    id("checkstyle")
    id("jacoco")
    id("org.sonarqube") version "6.0.1.5171"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:$junitVersion"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitApiVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitApiVersion")
    testImplementation("org.assertj:assertj-core:$assertjCoreVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${junitEngineVersion}")
}

testing {
    suites {
        getting(JvmTestSuite::class) {
            useJUnitJupiter()
        }
    }
}

tasks {
    test {
        useJUnitPlatform()
        finalizedBy(jacocoTestReport)
    }

    jacocoTestReport {
        dependsOn(test)
        reports {
            xml.required.set(true)
            html.required.set(true)
        }
    }
}

sonar {
    properties {
        property("sonar.projectKey", "neutrall85_java-project-78")
        property("sonar.organization", "neutrall85")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}
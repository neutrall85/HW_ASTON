val junitVersion = "5.11.0"
val junitApiVersion = "5.11.0"
val mockitoVersion = "5.19.0"
val lombokVersion = "1.18.38"
val jetbrainsAnnotationsVersion = "23.0.0"
val slf4jVersion = "2.0.7"
val logbackVersion = "1.5.13"

plugins {
    id("java")
    id("checkstyle")
}

group = "leo.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:$junitVersion"))
    testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitApiVersion")
    testImplementation("org.mockito:mockito-junit-jupiter:$mockitoVersion")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    compileOnly ("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
    implementation("org.jetbrains:annotations:$jetbrainsAnnotationsVersion")
    implementation("org.slf4j:slf4j-api:$slf4jVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
}

tasks.test {
    useJUnitPlatform()
}

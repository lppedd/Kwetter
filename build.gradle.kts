plugins {
    war
}

group = "org.payara.gradle"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    providedCompile("javax:javaee-api:8.0@jar") // Java EE API
    testCompile("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

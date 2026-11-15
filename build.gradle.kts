plugins {
    java
}

group = "com.example"
version = "1.1.0"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:26.3.build.+")
}

java {
    // A Paper 26.x-hez Java 25 kell
    toolchain.languageVersion.set(JavaLanguageVersion.of(25))
}

tasks.jar {
    archiveBaseName.set("DragonCrown")
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

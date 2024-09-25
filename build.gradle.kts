plugins {
    id("java")
}

group = "dev.xoapp.kappah"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.maven.apache.org/maven2/")
    maven("https://jitpack.io")
    maven("https://repo.opencollab.dev/maven-releases/")
    maven("https://repo.opencollab.dev/maven-snapshots/")
}

dependencies {
    compileOnly("com.github.DenzelCode:FormAPI:master")
    compileOnly("com.github.PowerNukkitX:PowerNukkitX:master-SNAPSHOT")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
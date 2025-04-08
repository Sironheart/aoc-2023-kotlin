plugins {
    kotlin("jvm") version "2.1.20"
}

sourceSets {
    main {
        kotlin.srcDir("src")
    }
}

tasks {
    wrapper {
        gradleVersion = "8.13"
    }
}

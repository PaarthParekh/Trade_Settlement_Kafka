plugins {
    java
    application
}

repositories {
    mavenCentral()
}

dependencies {
    // 1. JAXB Dependencies for XML serialization
    implementation("jakarta.xml.bind:jakarta.xml.bind-api:4.0.0")
    runtimeOnly("org.glassfish.jaxb:jaxb-runtime:4.0.0")

    // 2. Kafka Client
    implementation("org.apache.kafka:kafka-clients:3.7.0")

    // 3. Logging (Kafka uses SLF4J to log its connection status)
    implementation("org.slf4j:slf4j-simple:2.0.12")
}

application {
    // We will update this later once you name your main class
    mainClass.set("Main")
}
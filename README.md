# Trade_Settlement_Kafka

A Java-based messaging pipeline simulating a trading oms and kafka consumer. This project helps to see event-driven architecture by capturing trade data, serializing it into XML formats, and routing it through an Apache Kafka broker.

## Architecture

1. **Trade Publisher (Producer):** An CLI application that automatically creates Trade IDs and needs security name and quantity. It builds a Java object model and uses JAXB to marshal the data into a standardized XML envelope.
2. **Message Broker:** A local Apache Kafka and Zookeeper cluster running via Docker Compose.
3. **Settlement Consumer:** A persistent Java application subscribed to the Kafka topic, designed to catch and process incoming XML payloads in real time.

## Tech Stack
* **Language:** Java
* **Build System:** Gradle
* **Messaging:** Apache Kafka (Confluent Docker Images)
* **Serialization:** Jakarta XML Binding (JAXB)

## How to Run Locally

### 1. Start the Kafka Broker

Ensure Docker Desktop is running, then spin up the infrastructure:
```bash
docker-compose up -d
```

### 2. Start the Settlement Consumer
Run the SettlementConsumer.java class. It will connect to the broker and listen for incoming messages on the trade-settlement-queue topic.

### 3. Launch the Oms System
Run the Main.java class. This launches the interactive command-line interface. Follow the prompts to input security details, and order quantity and watch the generated XML publish to Kafka and instantly appear in the Consumer's console.

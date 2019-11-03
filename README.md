# Local Kafka stack and tester app

Adapted from 
[here](https://github.com/simplesteph/kafka-stack-docker-compose)
and 
[here](https://github.com/wkorando/event-stream-kafka)

## Usage

Start Kafka stack: `docker-compose up`

From `kafka-tester`-directory start the app: `mvn spring-boot:run`

Messages can be sent at `http://localhost:44444/send/{message}`

Messages can be read at `http://localhost:44444/messages`

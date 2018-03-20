# SpringBootKafkaDemo

This includes 2 Spring Boot application that interacts using Kafka Messaging(Topics)

1. Create 2 Topics : request-topic and response-topic

2. The URL to access the Restwebservice : http://localhost:8850/customer/details/getDetails?idNumber=27858589

The webservice on Request Application gets the ID from rest api and writes to request-queue and sleeps for 10 seconds.

The Response Application has a listens to the request-topic for any incoming request, fetches the data passed and processes and
returns the response to the response-queue.

The Request Application gets the storeged response received from Topic Listener and response to the API request

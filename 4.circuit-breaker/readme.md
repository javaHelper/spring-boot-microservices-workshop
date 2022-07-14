# Implement CB using Resilience 4J

- Eureka: http://localhost:8761/

<img width="1504" alt="Screenshot 2022-07-13 at 11 11 44 PM" src="https://user-images.githubusercontent.com/54174687/178908065-65e484d6-2b86-410b-9791-b2bbfc923a7f.png">

Make sure all microservices are up and running, below sequence to start microservices

- config-service
- discovery-server
- stock-service
- stock-daily-record-service
- gateway-service

GET -> `localhost:8011/stock-service/stocks/1`

Response:

```json
{
    "stockId": 1,
    "stockCode": "7052",
    "stockName": "PADINI",
    "dailyRecordModels": [
        {
            "recordId": 1,
            "stockId": 1,
            "priceOpen": 1.2,
            "priceClose": 1.1,
            "priceChange": 10.0,
            "volume": 3000000,
            "date": "2022-03-03T00:00:00.000+00:00"
        },
        {
            "recordId": 2,
            "stockId": 1,
            "priceOpen": 2.5,
            "priceClose": 10.0,
            "priceChange": 7.5,
            "volume": 4000000,
            "date": "2022-02-02T00:00:00.000+00:00"
        }
    ]
}
```

Now stop the `stock-daily-record-service`, you should see service has been unregister from the service registry, now again hit the below 

GET -> `localhost:8011/stock-service/stocks/1`

Response:

```json
{
    "stockId": 1,
    "stockCode": "7052",
    "stockName": "PADINI",
    "dailyRecordModels": []
}
```
Logs on console:

<img width="1398" alt="Screenshot 2022-07-14 at 11 14 11 AM" src="https://user-images.githubusercontent.com/54174687/178908647-10c046fd-37a4-4ef0-a5bf-8147151d9639.png">

```properties
# Actuator
management.endpoints.web.exposure.include=*

# Resilience4J
resilience4j.circuitbreaker.instances.albums-ws.failure-rate-threshold=50
resilience4j.circuitbreaker.instances.albums-ws.automatic-transition-from-open-to-half-open-enabled=true
resilience4j.circuitbreaker.instances.albums-ws.wait-duration-in-open-state=10000ms
resilience4j.circuitbreaker.instances.albums-ws.sliding-window-type=COUNT_BASED
resilience4j.circuitbreaker.instances.albums-ws.sliding-window-size=2
resilience4j.circuitbreaker.instances.albums-ws.minimum-number-of-calls=10
resilience4j.circuitbreaker.instances.albums-ws.event-consumer-buffer-size=10
```

Now again start the `stock-daily-record-service`, you should see the whole output again.


GET -> `http://localhost:8011/stock-service/actuator/circuitbreakerevents`

Response:

```json
{
    "circuitBreakerEvents": [
        {
            "circuitBreakerName": "stock-daily-record-service",
            "type": "SUCCESS",
            "creationTime": "2022-07-14T12:20:51.352136+05:30[Asia/Kolkata]",
            "errorMessage": null,
            "durationInMs": 496,
            "stateTransition": null
        },
        {
            "circuitBreakerName": "stock-daily-record-service",
            "type": "SUCCESS",
            "creationTime": "2022-07-14T12:20:57.211763+05:30[Asia/Kolkata]",
            "errorMessage": null,
            "durationInMs": 19,
            "stateTransition": null
        },
        {
            "circuitBreakerName": "stock-daily-record-service",
            "type": "ERROR",
            "creationTime": "2022-07-14T12:21:19.547042+05:30[Asia/Kolkata]",
            "errorMessage": "feign.RetryableException: Connection refused (Connection refused) executing GET http://stock-daily-record-service/stocks/1/stock-daily-record",
            "durationInMs": 5,
            "stateTransition": null
        },
        {
            "circuitBreakerName": "stock-daily-record-service",
            "type": "ERROR",
            "creationTime": "2022-07-14T12:21:45.258117+05:30[Asia/Kolkata]",
            "errorMessage": "feign.FeignException$ServiceUnavailable: [503] during [GET] to [http://stock-daily-record-service/stocks/1/stock-daily-record] [StockDailyRecordServiceClient#stockRecords(Integer)]: [Load balancer does not contain an instance for the service stock-daily-record-service]",
            "durationInMs": 7,
            "stateTransition": null
        },
        {
            "circuitBreakerName": "stock-daily-record-service",
            "type": "ERROR",
            "creationTime": "2022-07-14T12:21:56.203654+05:30[Asia/Kolkata]",
            "errorMessage": "feign.FeignException$ServiceUnavailable: [503] during [GET] to [http://stock-daily-record-service/stocks/1/stock-daily-record] [StockDailyRecordServiceClient#stockRecords(Integer)]: [Load balancer does not contain an instance for the service stock-daily-record-service]",
            "durationInMs": 1,
            "stateTransition": null
        },
        {
            "circuitBreakerName": "stock-daily-record-service",
            "type": "SUCCESS",
            "creationTime": "2022-07-14T12:22:52.351694+05:30[Asia/Kolkata]",
            "errorMessage": null,
            "durationInMs": 322,
            "stateTransition": null
        }
    ]
}
```




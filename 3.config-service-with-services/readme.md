# Implement Config Server with services

- Config-service

```properties
spring.application.name=config-server
server.port=8012

spring.cloud.config.server.git.uri=https://github.com/javaHelper/spring-boot-microservices-workshop
spring.cloud.config.server.git.search-paths=stock-records-cconfig
spring.cloud.config.server.git.clone-on-start=true
spring.cloud.config.server.git.default-label=main
spring.cloud.config.server.git.force-pull=true

```

GET ->
```
curl --location --request GET 'localhost:8012/config-service/properties'
```

Response:

```json
{
    "name": "config-service",
    "profiles": [
        "properties"
    ],
    "label": null,
    "version": "1f77fc4059830f1ecda82007e7971917f8ab250e",
    "state": null,
    "propertySources": [
        {
            "name": "https://github.com/javaHelper/spring-boot-microservices-workshop/stock-records-cconfig/application.properties",
            "source": {
                "eureka.client.serviceUrl.defaultZone": "http://localhost:8761/eureka",
                "eureka.instance.instance-id": "${spring.application.name}:${spring.application.instance_id:${random.value}}",
                "spring.datasource.url": "jdbc:mysql://localhost:3306/stock_app?createDatabaseIfNotExist=true&serverTimezone=UTC",
                "spring.datasource.username": "root",
                "spring.datasource.password": "Password",
                "spring.jpa.hibernate.ddl-auto": "update",
                "spring.jpa.properties.hibernate.show_sql": "true",
                "spring.jpa.properties.hibernate.use_sql_comments": "true",
                "spring.jpa.properties.hibernate.format_sql": "true"
            }
        }
    ]
}
```


- Eureka: http://localhost:8761/

<img width="1504" alt="Screenshot 2022-07-13 at 11 11 44 PM" src="https://user-images.githubusercontent.com/54174687/178806230-e321290d-06f5-4dea-b1f1-4c550455d16f.png">

GET -> 

```sh
curl --location --request GET 'localhost:8011/stock-service/stocks/1'
```

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

Use `spring.config.import=configserver:http://localhost:8012/`

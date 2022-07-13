# Inter-service communication using APIGW and Feign Client

- Eureka: http://localhost:8761/

<img width="1504" alt="Screenshot 2022-07-13 at 11 11 44 PM" src="https://user-images.githubusercontent.com/54174687/178796806-068c01ca-33b7-45f1-aaff-cc87cd2e293c.png">

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

# Inter-service communication using Feign

- Feign Logic

```java
@FeignClient(name = "stock-daily-record-service")
public interface StockDailyRecordServiceClient {


	@GetMapping("stocks/{stockId}/stock-daily-record")
	List<StockDailyRecordModel> stockRecords(@PathVariable("stockId") Integer stockId);
}
```

- Service Calling Logic

```java
@Override
	public StockDto getStockByStockId(Integer stockId) {
		 Optional<Stock> optionalStock = stockRepository.findById(stockId);
		 
		 if(!optionalStock.isPresent()) {
			 throw new StockNotFoundException("Stock Not Found");
		 }
		 
		 StockDto stockDto = modelMapper.map(optionalStock.get(), StockDto.class);
		 
		 // Inter-service communication using Feign Client
		 List<StockDailyRecordModel> records = dailyRecordServiceClient.stockRecords(stockId);
		 
		 stockDto.setDailyRecordModels(records);
		 
		 return stockDto;
	}
```


- Eureak: http://localhost:8761/

<img width="1504" alt="Screenshot 2022-07-13 at 11 11 44 PM" src="https://user-images.githubusercontent.com/54174687/178797226-03c769ed-f797-4396-9321-c9a792cf9bdb.png">


GET -> 

```sh
curl --location --request GET 'localhost:8080/stocks/1'
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

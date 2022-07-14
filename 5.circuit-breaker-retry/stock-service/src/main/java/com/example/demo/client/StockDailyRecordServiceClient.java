package com.example.demo.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.model.StockDailyRecordModel;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@FeignClient(name = "stock-daily-record-service")
public interface StockDailyRecordServiceClient {

	@GetMapping("stocks/{stockId}/stock-daily-record")
	@Retry(name = "stock-daily-record-service")
	@CircuitBreaker(name = "stock-daily-record-service", fallbackMethod = "getStockRecords")
	List<StockDailyRecordModel> stockRecords(@PathVariable("stockId") Integer stockId);
	
	default List<StockDailyRecordModel> getStockRecords(Integer stockId, Throwable throwable){
		System.out.println("### Param = "+ stockId);
		System.out.println("### Exception Took Place = "+ throwable.getMessage());
		return new ArrayList<>();
	}
}

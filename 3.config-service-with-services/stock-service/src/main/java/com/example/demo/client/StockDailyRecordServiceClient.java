package com.example.demo.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.model.StockDailyRecordModel;

@FeignClient(name = "stock-daily-record-service")
public interface StockDailyRecordServiceClient {


	@GetMapping("stocks/{stockId}/stock-daily-record")
	List<StockDailyRecordModel> stockRecords(@PathVariable("stockId") Integer stockId);
}

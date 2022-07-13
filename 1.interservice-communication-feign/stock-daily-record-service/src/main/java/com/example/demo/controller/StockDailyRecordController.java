package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.StockDailyRecordModel;
import com.example.demo.service.StockDailyRecordService;

@RestController
@RequestMapping("stocks/{stockId}/stock-daily-record")
public class StockDailyRecordController {
	@Autowired
	private StockDailyRecordService stockDailyRecordService;
	
	@GetMapping
	public List<StockDailyRecordModel> stockRecords(@PathVariable("stockId") Integer stockId){
		return stockDailyRecordService.findRecordsByStockId(stockId);
	}
}

package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.StockDto;
import com.example.demo.service.StockService;

@RestController
@RequestMapping("/stocks")
public class StockController {

	@Autowired
	private StockService stockService;

	@GetMapping("/{stockId}")
	public ResponseEntity<StockDto> getStock(@PathVariable("stockId") Integer stockId) {
		StockDto stockDto = stockService.getStockByStockId(stockId);
		
		return ResponseEntity.status(HttpStatus.OK).body(stockDto);
	}
}

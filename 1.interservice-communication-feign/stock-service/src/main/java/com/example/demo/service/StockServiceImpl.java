package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.client.StockDailyRecordServiceClient;
import com.example.demo.dto.StockDto;
import com.example.demo.entity.Stock;
import com.example.demo.exception.StockNotFoundException;
import com.example.demo.model.StockDailyRecordModel;
import com.example.demo.repository.StockRepository;


@Service
public class StockServiceImpl implements StockService {
	
	@Autowired
	private StockRepository stockRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private StockDailyRecordServiceClient dailyRecordServiceClient;

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

}

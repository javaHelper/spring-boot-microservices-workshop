package com.example.demo.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.StockDailyRecord;
import com.example.demo.model.StockDailyRecordModel;
import com.example.demo.repository.StockDailyRecordRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StockDailyRecordServiceImpl implements StockDailyRecordService {

	@Autowired
	private StockDailyRecordRepository stockDailyRecordRepository;

	@Override
	public List<StockDailyRecordModel> findRecordsByStockId(Integer stockId){
		List<StockDailyRecord> dailyRecords = stockDailyRecordRepository.findByStockId(stockId);
		
		if(dailyRecords == null || dailyRecords.isEmpty()) {
			return new ArrayList<>();
		}
		
		Type listType = new TypeToken<List<StockDailyRecordModel>>() {}.getType();
		
		List<StockDailyRecordModel> dailyRecordModels = new ModelMapper().map(dailyRecords, listType);
		log.info("Returning " + dailyRecordModels.size() + " Stock Records");
		return dailyRecordModels;
	}
}

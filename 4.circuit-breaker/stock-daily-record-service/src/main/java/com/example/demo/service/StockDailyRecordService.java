package com.example.demo.service;

import java.util.List;

import com.example.demo.model.StockDailyRecordModel;

public interface StockDailyRecordService {

	List<StockDailyRecordModel> findRecordsByStockId(Integer stockId);

}

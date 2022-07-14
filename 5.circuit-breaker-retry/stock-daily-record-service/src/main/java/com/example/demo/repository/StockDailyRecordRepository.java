package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.StockDailyRecord;

public interface StockDailyRecordRepository extends JpaRepository<StockDailyRecord, Integer>{
	List<StockDailyRecord> findByStockId(Integer stockId);
}

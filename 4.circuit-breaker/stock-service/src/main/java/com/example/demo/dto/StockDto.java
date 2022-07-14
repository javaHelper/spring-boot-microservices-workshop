package com.example.demo.dto;

import java.util.List;

import com.example.demo.model.StockDailyRecordModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StockDto {
	private Integer stockId;
	private String stockCode;
	private String stockName;
	List<StockDailyRecordModel> dailyRecordModels;
}

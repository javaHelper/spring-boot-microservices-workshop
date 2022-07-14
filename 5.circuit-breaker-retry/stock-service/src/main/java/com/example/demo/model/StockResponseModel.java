package com.example.demo.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StockResponseModel {
	private Integer stockId;
	private String stockCode;
	private String stockName;
	private List<StockDailyRecordResponseModel> stockDailyRecords;
}

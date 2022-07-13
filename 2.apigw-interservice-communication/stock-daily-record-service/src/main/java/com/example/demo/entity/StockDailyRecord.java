package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class StockDailyRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RECORD_ID", unique = true, nullable = false)
	private Integer recordId;
	
	@Column(name = "STOCK_ID", unique = false, nullable = true)
	private Integer stockId;
	
	@Column(name = "PRICE_OPEN", precision = 6)
	private Float priceOpen;
	
	@Column(name = "PRICE_CLOSE", precision = 6)
	private Float priceClose;
	
	@Column(name = "PRICE_CHANGE", precision = 6)
	private Float priceChange;
	
	@Column(name = "VOLUME")
	private Long volume;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE", unique = true, nullable = false, length = 10)
	private Date date;
}

package com.ybs.seckil.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Orders implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
	
	private String courseNo;
	
	private String username;
	
	private String courseName;
	
	private BigDecimal coursePrice;
	
	private BigDecimal payPrice;
	
	private String payment;
	
	private String payStatus;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date payDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	private String createBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;
	
	private String updateBy;

	private String coursePic;
}

package com.ybs.seckil.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Course implements Serializable{
	private static final long serialVersionUID = 7936554690434155590L;
	@Id
	private String courseNo;
	
	private String courseName;
	
	private String teacherName;
	
	private String courseDesciption;
	
	private String coursePeriod;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date endTime;
	
	private BigDecimal courcePrice;
	
	private Integer stockQuantity;

	private Integer courseType;
	
	private String coursePic;}

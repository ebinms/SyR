package com.syr.defaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface SyrDefault {
	
	int INT	= 0;
	double DOUBLE		= 0.0;
	long LONG			= 0L;
	float FLOAT			= 0.0f;
	String HASH			= "#";
	String NULL			= "";
	String STRING		= "#";
	String DATE_TIME_FORMAT 	= "yyyy-MM-dd HH:mm:ss";
	String DATE_FORMAT 	= "yyyy-MM-dd HH:mm:ss";
	LocalDateTime PAST_DEF_DATE	= LocalDateTime.of(1900, 01, 01, 00, 00, 00);
	LocalDateTime FUTURE_DEF_DATE = LocalDateTime.of(9999, 01, 01, 00, 00, 00);
	int ID_NUM_PART_LENGTH	= 10;
	
	static LocalDateTime CURRENT_DATE_TIME(){
		try{
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
			return LocalDateTime.parse(LocalDateTime.now().format(dtf), dtf);
		}catch (Exception e) {
			System.out.println("Ex................"+e.getMessage());
			return LocalDateTime.now();
		}
	}
	
	static LocalDate CURRENT_DATE(){
		try{
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_FORMAT);
			return LocalDate.parse(LocalDateTime.now().format(dtf), dtf);
		}catch (Exception e) {
			System.out.println("Ex................"+e.getMessage());
			return LocalDate.now();
		}
	}
}

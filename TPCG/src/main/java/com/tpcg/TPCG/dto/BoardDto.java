package com.tpcg.TPCG.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {
	
	private long id;
	@NonNull 
	private String title;
	@NonNull
	private String content;
	
}

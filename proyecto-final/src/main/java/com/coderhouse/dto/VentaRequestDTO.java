package com.coderhouse.dto;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VentaRequestDTO {
	
    private Long clienteId;
    
    private Map<String, Integer> productos; 
    
}
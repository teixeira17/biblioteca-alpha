package com.teixeira.biblioteca.alpha.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author lucas
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AutorResponse {
    
    public String name;
    
}

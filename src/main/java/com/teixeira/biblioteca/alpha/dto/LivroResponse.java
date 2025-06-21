package com.teixeira.biblioteca.alpha.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

/**
 *
 * @author lucas
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LivroResponse {
    
    public String title;
    public String publish_date;
    public List<String> publishers;
    public List<AuthorReference> authors;
    
    
}

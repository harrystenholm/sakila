package com.example.sakila.dto.input;

import lombok.Data;

@Data
public class PageInput {

    private Integer pageNo;
    private Integer pageSize;
    String sortBy;

}

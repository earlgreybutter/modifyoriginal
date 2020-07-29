package com.magicwater.springbook.domain;

import lombok.Data;

@Data
public class Search {
    private String searchCondition;
    private String searchKeyword;
}
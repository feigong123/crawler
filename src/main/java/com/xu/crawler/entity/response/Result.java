package com.xu.crawler.entity.response;

import lombok.Data;

@Data
public class Result {
    private Integer statusCode;
    private String message;
    private Object data;
}

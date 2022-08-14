package com.xu.crawler.entity.request;

import lombok.Data;

import java.util.List;

@Data
public class SearchContentFromUrlsByKeyReq {
    private String key;
    private List<String> urls;
}

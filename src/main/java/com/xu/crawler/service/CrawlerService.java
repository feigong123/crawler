package com.xu.crawler.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface CrawlerService {
    List<JSONObject> searchContentFromUrlsByKey(String key, List<String> urls) throws Exception;
}

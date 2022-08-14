package com.xu.crawler.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xu.crawler.service.CrawlerService;
import com.xu.crawler.util.HtmlParseUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CrawlerServiceImpl implements CrawlerService {
    @Override
    public List<JSONObject> searchContentFromUrlsByKey(String key, List<String> urls) throws Exception {
        List<JSONObject> matchedContents = new ArrayList<>();
        for (String url : urls) {
            JSONObject matchedJson = HtmlParseUtil.searchContentFromUrlByKey(key, url);
            matchedContents.add(matchedJson);
        }

        return matchedContents;
    }
}

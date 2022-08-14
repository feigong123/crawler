package com.xu.crawler.controller;

import com.alibaba.fastjson.JSONObject;
import com.xu.crawler.entity.request.SearchContentFromUrlsByKeyReq;
import com.xu.crawler.entity.response.Result;
import com.xu.crawler.service.CrawlerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/crawler")
@Api(value = "crawler", tags = "crawler operations")
public class CrawlerController {
    @Autowired
    private CrawlerService crawlerService;

    @ApiOperation(value = "Search related content from the ingested urls by the key word", notes = "Search related content from the ingested urls by the key word")
    @PostMapping("/searchContentFromUrlsByKey")
    public @ResponseBody
    Result searchContentFromUrlsByKey(@RequestBody SearchContentFromUrlsByKeyReq searchContentFromUrlsByKeyReq) {
        Result result = new Result();
        try {
            List<JSONObject> matchContents = crawlerService.searchContentFromUrlsByKey(searchContentFromUrlsByKeyReq.getKey(), searchContentFromUrlsByKeyReq.getUrls());
            result.setStatusCode(HttpStatus.OK.value());
            result.setMessage(HttpStatus.OK.toString());
            result.setData(matchContents);
        } catch (Exception exception) {
            result.setStatusCode(HttpStatus.EXPECTATION_FAILED.value());
            result.setMessage(exception.getMessage());
        }

        return result;
    }

}

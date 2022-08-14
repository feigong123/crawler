package com.xu.crawler.util;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.StrKit;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;

import java.net.URLEncoder;

import static org.springframework.http.HttpHeaders.USER_AGENT;

public class HtmlParseUtil {
    public static JSONObject searchContentFromUrlByKey(String key, String url) throws Exception {
        JSONObject matchedJson = new JSONObject();

        Connection conn = getConnection(url);
        Document document = conn.get();

        String simpleHtml = getText(document.html());
        String markedSimpleHtml = markKeywods(key, simpleHtml);

        matchedJson.put(url, markedSimpleHtml);

        return matchedJson;
    }

    public static Connection getConnection(String url) throws Exception {
        HttpsUtil.trustEveryone();

        Connection connect = Jsoup.connect(url).userAgent(USER_AGENT);
        connect.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        connect.header("Accept-Encoding", "gzip, deflate, sdch");
        connect.header("Accept-Language", "zh-CN,zh;q=0.8");
        connect.timeout(30000);
        connect.ignoreHttpErrors(true);

        return connect;
    }

    // Only full text is allowed
    public static String getText(String html) {
        if (html == null)
            return null;
        return Jsoup.clean(html, Whitelist.none()).replace("&nbsp;", "");
    }

    // The following tabs are available through
    // b, em, i, strong, u. full text
    public static String getSimpleHtml(String html) {
        if (html == null)
            return null;
        return Jsoup.clean(html, Whitelist.simpleText());
    }

    // The following tabs are available through
    //a, b, blockquote, br, cite, code, dd, dl, dt, em, i, li, ol, p, pre, q, small, strike, strong, sub, sup, u, ul
    public static String getBasicHtml(String html) {
        if (html == null)
            return null;
        return Jsoup.clean(html, Whitelist.basic());
    }

    //On the basis, add pictures through
    public static String getBasicHtmlandimage(String html) {
        if (html == null)
            return null;
        return Jsoup.clean(html, Whitelist.basicWithImages());
    }

    // The following tabs are available through
    //a, b, blockquote, br, caption, cite, code, col, colgroup, dd, dl, dt, em, h1, h2, h3, h4, h5, h6, i, img, li, ol, p, pre, q, small, strike, strong, sub, sup, table, tbody, td, tfoot, th, thead, tr, u, ul
    public static String getFullHtml(String html) {
        if (html == null)
            return null;
        return Jsoup.clean(html, Whitelist.relaxed());
    }

    //Only specified HTML tags are allowed
    public static String clearTags(String html, String... tags) {
        Whitelist wl = new Whitelist();
        return Jsoup.clean(html, wl.addTags(tags));
    }

    // Color keywords
    public static String markKeywods(String keywords, String target) {
        if (StrKit.notBlank(keywords)) {
            String[] arr = keywords.split(" ");
            for (String s : arr) {
                if (StrKit.notBlank(s)) {
                    String temp = "<span class=\"highlight\">" + s + "</span>";
                    if (temp != null)
                        target = target.replaceAll(s, temp);
                }
            }
        }
        return target;
    }

    // Get img url from source html
    public static String getImgSrc(String html) {
        if (html == null)
            return null;
        Document doc = Jsoup.parseBodyFragment(html);
        Element image = doc.select("img").first();
        return image == null ? null : image.attr("src");
    }
}

package com.example;

import com.example.demo.util.HttpUtil;
import org.junit.Test;

/**
 * @author KPQ
 * @date 2021/10/26
 */
public class HttpRequestTest {

    @Test
    public void requestTest() {
        String html = HttpUtil.get("https://github.com/trending/");
        System.out.println(html);
    }

}

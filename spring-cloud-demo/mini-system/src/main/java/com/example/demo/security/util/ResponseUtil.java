package com.example.demo.security.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author KPQ
 * @date 2021/11/18
 */
@Slf4j
public class ResponseUtil {

    private static ObjectMapper om = new ObjectMapper();

    public static void response(HttpServletResponse response, ResultUtil result) {
        try (PrintWriter out = response.getWriter()) {
            response.reset();
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(result.getCode());
            out.write(om.writeValueAsString(result));
            out.flush();
        } catch (Exception e) {
            log.error("response error:{}", e.getMessage(), e);
        }
    }


}

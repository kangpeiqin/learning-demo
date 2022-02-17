package com.example.record.rest.web;

import cn.hutool.core.util.RandomUtil;
import com.example.record.domain.entity.Account;
import com.example.record.redisqueue.BizConstant;
import com.example.record.redisqueue.RedisOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author KPQ
 * @date 2022/2/16
 */
@RestController
@RequestMapping("/api/redisQueue")
@RequiredArgsConstructor
@Slf4j
public class RedisQueueTest {

    private final RedisOperation redisOperation;

    @PostMapping(value = "code")
    public String sendCode(@RequestBody @Valid Account dto) {
        final String code = RandomUtil.randomNumbers(4);
        dto.setCode(code);
        //应用场景：如获取验证码，需向第三发服务发送短信，
        // 利用消息队列进行异步发送,详见：CodeConsumer 类
        redisOperation.leftPush(BizConstant.CODE_QUEUE_KEY, dto);
        return "success";
    }

}

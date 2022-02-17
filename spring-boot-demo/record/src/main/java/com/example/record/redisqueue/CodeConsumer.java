package com.example.record.redisqueue;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.record.domain.entity.Account;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author KPQ
 * @date 2022/2/16
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class CodeConsumer {

    private final RedisTemplate<String, Serializable> redisTemplate;

    @PostConstruct
    public void sendCode() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("code-consumer-pool-%d").build();
        ExecutorService executorService = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024), threadFactory);
        executorService.execute(() -> {
            while (true) {
                try {
                    blockingConsume();
                } catch (Exception e) {
                    log.error("消费线程异常{}", e.getMessage(), e);
                }
            }
        });
    }

    public void blockingConsume() {
        //使用 bbRPop 防止 CPU 空转，Redis BRpop 命令移出并获取列表的最后一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
        List<Object> data = redisTemplate.executePipelined((RedisCallback<Account>) connection -> (Account) connection.bRPop(BizConstant.TIME_OUT, BizConstant.CODE_QUEUE_KEY.getBytes()), new StringRedisSerializer());

        data.forEach(o -> {
            if (o == null) {
                return;
            }
            log.info("-------消费验证码--------:{}", o);
            if (o instanceof ArrayList) {
                JSONObject jsonObject = JSON.parseObject(((ArrayList) o).get(1).toString());
                Account account = new Account();
                account.setAccount(jsonObject.getString("account"));
                account.setCode(jsonObject.getString("code"));
                send(account);
            }
        });
    }

    private void send(Account account) {
        try {
            long start = System.currentTimeMillis();
            log.info("开始向手机号:{},发送短信验证码:{}", account.getAccount(), account.getCode());
            Thread.sleep(2000);
            long end = System.currentTimeMillis();
            log.info("短息发送结束，总耗时：{}", end - start);
        } catch (Exception e) {
            log.error("-----发送短信失败----");
        }
    }


}

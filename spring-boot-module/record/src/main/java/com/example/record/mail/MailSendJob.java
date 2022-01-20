package com.example.record.mail;

import cn.hutool.core.util.RandomUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

/**
 * @author KPQ
 * @date 2022/1/19
 */
@Component
@ConditionalOnProperty(value = {"scheduling.enabled"}, havingValue = "true")
@RequiredArgsConstructor
@Slf4j
public class MailSendJob {

    private final JavaMailSender mailSender;

    private final MailProperties mailProperties;

    @Scheduled(cron = "${scheduling.daily-job}")
    public void sendMail() {
        final String code = RandomUtil.randomNumbers(6);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        //邮件设置
        try {
            log.info("发送邮件");
            helper.setFrom(mailProperties.getUsername());
            helper.setTo("xxxxxx@qq.com");
            //邮件主题
            helper.setSubject("注册验证码");
            helper.setText(code);
            mailSender.send(message);
        } catch (Exception e) {
            log.error("发送邮件异常{}", e.getMessage(), e);
        }
    }
}

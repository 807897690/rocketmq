package com.wzy.consumer;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName TestConsumer
 * Description  TODO
 * @Author Administrator
 * @Date 2020/12/14 20:38
 * @Version 1.0
 */
@Component
@RocketMQMessageListener(topic = "test", consumerGroup = "testGroup")
public class TestConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String s) {
        System.out.println(s);
    }
}

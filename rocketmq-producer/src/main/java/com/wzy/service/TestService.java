package com.wzy.service;

import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

import java.util.Map;

/**
 * @ClassName TestService
 * Description  TODO
 * @Author Administrator
 * @Date 2020/12/14 20:43
 * @Version 1.0
 */
public interface TestService {

    /**
     * 执行事务
     * @param message
     * @return
     */
    RocketMQLocalTransactionState insert(Message message);

    /**
     * 回调校验
     * @param id
     * @return
     */
    int checkTransaction(String id);

    /**
     * 发起事务
     * @param map
     */
    void sendTransactionMessage(Map map);

}

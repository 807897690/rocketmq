package com.wzy.service.listener;

import com.alibaba.fastjson.JSON;
import com.wzy.service.TestService;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ClassName TransactionMessageListener
 * Description  TODO
 * @Author Administrator
 * @Date 2020/12/14 21:00
 * @Version 1.0
 */
@RocketMQTransactionListener
public class TransactionMessageListener implements RocketMQLocalTransactionListener {

    @Autowired
    private TestService testService;

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        try {
            System.out.println("开始MQ事务");
            return testService.insert(message);
        }catch (Exception e) {
            System.out.println("MQ事务回滚");
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        Map map = JSON.parseObject(new String((byte[]) message.getPayload()), Map.class);
        if(testService.checkTransaction(String.valueOf(map.get("id"))) > 0) {
            System.out.println("事务回调提交");
            return RocketMQLocalTransactionState.COMMIT;
        }
        System.out.println("事务回调回滚");
        return RocketMQLocalTransactionState.ROLLBACK;
    }
}

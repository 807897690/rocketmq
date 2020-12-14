package com.wzy.service.impl;

import com.alibaba.fastjson.JSON;
import com.wzy.mapper.UserMapper;
import com.wzy.service.TestService;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @ClassName TestServiceImpl
 * Description  TODO
 * @Author Administrator
 * @Date 2020/12/14 20:43
 * @Version 1.0
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public RocketMQLocalTransactionState insert(Message message) {
        System.out.println("发送的消息为："+ new String((byte[]) message.getPayload()));
        Map map = JSON.parseObject(new String((byte[]) message.getPayload()), Map.class);
        RocketMQLocalTransactionState state = RocketMQLocalTransactionState.COMMIT;
        if(!userMapper.insert(map)) {
            state = RocketMQLocalTransactionState.ROLLBACK;
        } else if("100".equals(map.get("id"))) {
            state = RocketMQLocalTransactionState.ROLLBACK;
        } else if("101".equals(map.get("id"))) {
            state = RocketMQLocalTransactionState.UNKNOWN;
        }
        return state;
    }

    @Override
    public int checkTransaction(String id) {
        return userMapper.selectById(id);
    }

    @Override
    public void sendTransactionMessage(Map map) {
        Message message = MessageBuilder.withPayload(JSON.toJSONString(map)).build();
        rocketMQTemplate.sendMessageInTransaction("test", message, null);
    }
}

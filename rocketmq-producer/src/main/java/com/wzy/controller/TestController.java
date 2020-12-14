package com.wzy.controller;

import com.wzy.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName TestController
 * Description  TODO
 * @Author Administrator
 * @Date 2020/12/14 20:42
 * @Version 1.0
 */
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/test.do")
    public Object test(@RequestParam Map<String, Object> param) {
        testService.sendTransactionMessage(param);
        return "操作成功";
    }

}

package com.xtan.mycloud.controller;

import com.xtan.mycloud.entities.CommonResult;
import com.xtan.mycloud.entities.Payment;
import com.xtan.mycloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/addPayment")
    public CommonResult addPayment(@RequestBody Payment payment) {
        log.info("开始创建--" + payment.toString());
        int i = paymentService.addPayment(payment);
        CommonResult commonResult = null;
        if (i > 0) {
            commonResult = new CommonResult<>(200, "插入成功", i);
        } else {
            commonResult = new CommonResult<>(200, "插入失败", 0);
        }
        return commonResult;
    }


    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        log.info("开始查询---" + id);
        Payment payment = paymentService.getPaymentById(id);
        log.info("返回--" + payment.toString());

        CommonResult<Payment> commonResult = null;
        if (payment != null) {
            commonResult = new CommonResult<>(200, "查询成功:" +serverPort, payment);
        } else {
            commonResult = new CommonResult<>(200, "查询失败", null);
        }
        return commonResult;
    }
}

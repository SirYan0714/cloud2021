package com.xtan.mycloud.service.fallback;

import com.xtan.mycloud.service.PaymentHystrixService;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService {
    private String msg = "调用服务出错或超时------";
    @Override
    public String paymentInfo_OK(Integer id) {
        return msg;
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return msg;
    }
}

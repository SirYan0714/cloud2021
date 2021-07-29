package com.xtan.mycloud.controller;


import com.xtan.mycloud.entities.CommonResult;
import com.xtan.mycloud.entities.Payment;
import com.xtan.mycloud.lb.LoadBalancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
public class ConsumerController {

    public static final String PaymentSrv_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private DiscoveryClient discoveryClient;
    @Resource
    private LoadBalancer loadBalancer;


    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/addPayment") //客户端用浏览器是get请求，但是底层实质发送post调用服务端8001
    public CommonResult addPayment(@RequestBody Payment payment) {
        return restTemplate.postForObject(PaymentSrv_URL + "/payment/addPayment", payment, CommonResult.class);
    }


    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPayment(@PathVariable Long id) {
        return restTemplate.getForObject(PaymentSrv_URL + "/payment/get/" + id, CommonResult.class, id);
    }

    @GetMapping("/consumer/payment/lb")
    public String getPaymentLB()
    {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");

        if(instances == null || instances.size()<=0) {
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();

        return restTemplate.getForObject(uri+"/payment/lb",String.class);
    }




}

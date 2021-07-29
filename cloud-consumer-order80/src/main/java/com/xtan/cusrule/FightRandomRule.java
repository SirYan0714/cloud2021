package com.xtan.cusrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;

public class FightRandomRule {
    @Bean
    public IRule myRule()
    {
        return new RandomRule();//定义为随机
    }

}

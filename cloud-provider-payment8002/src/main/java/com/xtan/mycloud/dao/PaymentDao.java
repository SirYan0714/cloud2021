package com.xtan.mycloud.dao;

import com.xtan.mycloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {

    public int addPayment(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}

package com.techworld.PaymentService.data.repository;

import com.techworld.PaymentService.data.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,String> {
}

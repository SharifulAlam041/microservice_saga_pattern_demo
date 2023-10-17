package com.techworld.PaymentService.events;

import com.techworld.CommonService.events.PaymentProcessedEvent;
import com.techworld.PaymentService.data.entity.Payment;
import com.techworld.PaymentService.data.repository.PaymentRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PaymentEventsHandler {
    private PaymentRepository paymentRepository;
    public PaymentEventsHandler(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }
    @EventHandler
    public void on(PaymentProcessedEvent event) {
        Payment payment = Payment.builder()
                .paymentId(event.getPaymentId())
                .orderId(event.getOrderId())
                .paymentStatus("Completed")
                .timeStamp(new Date())
                .build();
        paymentRepository.save(payment);
    }
}

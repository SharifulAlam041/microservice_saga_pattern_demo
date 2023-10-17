package com.techworld.PaymentService.aggregate;

import com.techworld.CommonService.commands.ValidatePaymentCommand;
import com.techworld.CommonService.events.PaymentProcessedEvent;
import com.techworld.CommonService.model.CardDetails;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@Slf4j
public class PaymentAggregate {
    @AggregateIdentifier
    private String paymentId;
    private String orderId;
    private String paymentStatus;

    public PaymentAggregate() {

    }
    @CommandHandler
    public PaymentAggregate(ValidatePaymentCommand validatePaymentCommand) {
        log.info("Executing validate payment command for orderId: {} and paymentId: {}",
                validatePaymentCommand.getOrderId(),validatePaymentCommand.getPaymentId());
        PaymentProcessedEvent paymentProcessedEvent = new PaymentProcessedEvent(validatePaymentCommand.getOrderId(),
                validatePaymentCommand.getPaymentId());
        AggregateLifecycle.apply(paymentProcessedEvent);
        log.info("PaymentProcessedEvent applied");
    }
    @EventSourcingHandler
    public void on(PaymentProcessedEvent event){
        this.paymentId = event.getPaymentId();
        this.orderId = event.getOrderId();
    }
}

package com.techworld.OrderService.command.api.aggregate;

import com.techworld.OrderService.command.api.command.CreateOrderCommand;
import com.techworld.OrderService.command.api.events.OrderCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class OrderAggregate {
    @AggregateIdentifier
    private String orderId;
    private String productId;
    private String userId;
    private String addressId;
    private Integer quantity;
    private String orderStatus;

    public OrderAggregate() {

    }
    @CommandHandler
    public OrderAggregate(CreateOrderCommand createOrderCommand) {
//        Validate the command
        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent();
        BeanUtils.copyProperties(createOrderCommand,orderCreatedEvent);
        AggregateLifecycle.apply(orderCreatedEvent);
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent event) {
        this.addressId = event.getAddressId();
        this.orderId= event.getOrderId();
        this.orderStatus = event.getOrderStatus();
        this.quantity = event.getQuantity();
        this.userId = event.getUserId();
        this.productId = event.getProductId();
    }
}

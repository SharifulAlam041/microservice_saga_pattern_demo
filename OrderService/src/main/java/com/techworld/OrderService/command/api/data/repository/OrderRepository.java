package com.techworld.OrderService.command.api.data.repository;

import com.techworld.OrderService.command.api.data.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,String> {
}

package com.TTS.Service;

import com.TTS.Entity.Order;

import java.util.List;

public interface OrderService extends AuditService<Order,Integer> {
    List<Order> getOrderByUser(Integer idUser);

    List<Order> getListOrderByStatus(Integer Status);
}

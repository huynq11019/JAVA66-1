package com.TTS.Service;

import com.TTS.Entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderService extends AuditService<Order,Integer> {
    List<Order> getOrderByUser(Integer idUser);

    List<Order> getListOrderByStatus(Integer Status);

    Page<Order> getPage(int page, int limit, String sortBy, String order);

    Order updateStatus(Integer orderId, Integer status);
}

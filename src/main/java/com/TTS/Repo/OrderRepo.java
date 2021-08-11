package com.TTS.Repo;

import com.TTS.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo  extends JpaRepository<Order,Integer> {

    List<Order> findAllByAccountod_Id(Integer accountId);

    List<Order> findAllByStatus(Integer status);
}

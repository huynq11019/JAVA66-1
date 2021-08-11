package com.TTS.Repo;

import com.TTS.Entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepo extends JpaRepository<OrderDetail, Integer> {
        List<OrderDetail> findAllByFromOd_Id(Integer orderID);

}

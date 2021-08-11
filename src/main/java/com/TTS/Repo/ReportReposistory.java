package com.TTS.Repo;

import com.TTS.DTO.ReportDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;

@Service
@Slf4j
public class ReportReposistory {
    @PersistenceContext
    private EntityManager entityManager;

    public ReportDTO loadReport(Date startDate, Date endDate) {
        // số tiền, số sản phẩm, số đơn hàng
//        SELECT count(orderdetails.id),COUNT(DISTINCT  orders.id), SUM(orderdetails.price * orderdetails.quantity) FROM orders INNER JOIN orderdetails ON	orders.id = orderdetails.form_od
//        GROUP BY orders.id
        String hql = " SELECT count(orderdetails.id),COUNT(DISTINCT  orders.id), SUM(orderdetails.price * orderdetails.quantity) FROM orders INNER JOIN orderdetails ON\torders.id = orderdetails.form_od\n" +
                "GROUP BY orders.id";
        log.info(hql);
        Query laodReport = entityManager.createNativeQuery(hql, ReportDTO.class);
        System.out.println(laodReport.getSingleResult());
        return null;
    }

}

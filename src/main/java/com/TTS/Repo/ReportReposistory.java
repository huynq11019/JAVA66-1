package com.TTS.Repo;

import com.TTS.DTO.CateReportDTO;
import com.TTS.DTO.ProductReportDTO;
import com.TTS.DTO.ReportDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Slf4j
public class ReportReposistory {
    @PersistenceContext
    private EntityManager entityManager;

    // find category report

    // file Product report

    // find time report

    public ReportDTO getReport() {
//        String hql = "SELECT count(o.id) FROM Order o inner join OrderDetail  odt on o = odt.fromOd group by o.id";
        String hql = "SELECT  count(o.id),count(o.id),count(o.id)  FROM Order o ";

        Query query = entityManager.createQuery(hql);
        System.out.println(query.getSingleResult());
        return null;
    }

    public List<ProductReportDTO> getProductReport(Pageable pageable) {
//        String hql = "select products.id , count(distinct orderdetails.form_od), sum(orderdetails.price) from orderdetails inner join products on orderdetails.form_product = products.id\n" +
//                "group by products.id";

        String hql = "SELECT new com.TTS.DTO.ProductReportDTO( dt.fromProduct, count(distinct  dt.fromOd), sum(dt.price)) FROM OrderDetail dt group by  dt.fromProduct";
        Query query = entityManager.createQuery(hql);
        query.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        return query.getResultList();
    }

    public List<CateReportDTO> getCateReport(Pageable pageable) {
//        String hql = "SELECT dt.fromProduct.category, COUNT(distinct dt.fromProduct), SUM(dt.price) FROM OrderDetail dt group by dt.fromProduct.category";

        String hql = "SELECT new com.TTS.DTO.CateReportDTO(cate, COUNT(pro), COUNT(distinct odt.fromOd) )  FROM Category cate left join cate.ProductInCate pro left join pro.orderDetails odt group by  cate.id";
        Query query = entityManager.createQuery(hql);
        query.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
//        System.out.println(query.getResultList());
        return query.getResultList();
    }


}

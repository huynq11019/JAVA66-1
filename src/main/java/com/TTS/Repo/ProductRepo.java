package com.TTS.Repo;

import com.TTS.Entity.Product;
import com.TTS.Repo.CustomRepo.CustomProductRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>, CustomProductRepo {
    @Query("SELECT p FROM Product p WHERE p.category.id =?1 ")
    List<Product> findAllByCategory(Integer cate);
//    @Query("SELECT p FROM Product p WHERE p.orderDetails.size>0 ")
    @Query("SELECT p FROM Product p order by p.orderDetails.size DESC")
//    SELECT products.name_product, count(orderdetails.id) FROM products left outer join orderdetails on products.id = orderdetails.form_product
//    group by products.name_product
    List<Product> getListProductTrendy();
}

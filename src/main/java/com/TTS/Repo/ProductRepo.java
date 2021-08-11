package com.TTS.Repo;

import com.TTS.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE p.category.id =?1 ")
    List<Product> findAllByCategory(Integer cate);
    @Query("SELECT p FROM Product p order by p.orderDetails.size DESC")
    List<Product> getListProductTrendy();
    @Query("SELECT p FROM Product p WHERE lower(p.nameProduct) like  lower(?1)  ")
    List<Product> searchAllBykeyword( String keyword);

}

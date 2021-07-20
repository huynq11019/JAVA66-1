package com.TTS.Repo;

import com.TTS.Entity.Category;
import com.TTS.Entity.Product;
import com.TTS.Repo.CustomRepo.CustomProductRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>,CustomProductRepo {

    List<Product> findAllByCategory(Category cate);


}

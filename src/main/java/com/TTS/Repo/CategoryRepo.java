package com.TTS.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TTS.Entity.Category;
@Repository
public interface CategoryRepo  extends JpaRepository<Category, Integer>{

}

package com.TTS.Service;

import com.TTS.Entity.Category;

import java.util.List;

public interface CategoriesService extends  AuditService<Category,Integer> {
    public List<Category> loadAll();

}

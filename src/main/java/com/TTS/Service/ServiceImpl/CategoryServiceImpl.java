package com.TTS.Service.ServiceImpl;

import com.TTS.Entity.Category;
import com.TTS.Repo.CategoryRepo;
import com.TTS.Service.CategoriesService;
import com.TTS.Util.ParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

//import org.apache.log4j.Logger;

@Service
@Transactional 
public class CategoryServiceImpl implements CategoriesService {
//    private Logger _log = Logger.getLogger(CategoryServiceImpl.class);
    @Autowired
    private CategoryRepo cateRepo;
    @Autowired
    private ParamUtil param;
    @Override
    public Category create(Category entty) {
        if (entty.getId() == null){
            Integer idCreateBy  = param.getAccountId();
            entty.setCreatedBy(idCreateBy);
            return cateRepo.save(entty);
        }
        return null;
    }

    @Override
    public Category update(Category update) {
        //kiểm tra id có tồn tại
        //kiểm tra id có bị null
        if (update.getId() != null && cateRepo.existsById(update.getId())){
            Integer idcreate = param.getAccountId();
            update.setId(idcreate);
            return cateRepo.save(update);

        }
        return null;
    }

    @Override
    public Category Delete(Integer delete) {

        return null;
    }

    @Override
    public Category getbyID(Integer integer) {
        if (integer != null ){
            return cateRepo.findById(integer).get();
        }
        return null;
    }
    @Override
    public List<Category> loadAll(){
        //load tất cả category
        return cateRepo.findAll();
    };
}

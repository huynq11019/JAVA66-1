package com.TTS.Service.ServiceImpl;


import com.TTS.Entity.Product;
import com.TTS.Repo.ProductRepo;
import com.TTS.Service.ProductService;
import com.TTS.Util.ParamUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

@Service
@Slf4j
@Transactional
public class ProductServiceImpl implements ProductService {
    //    private static final org.apache.log4j.Logger _log = Logger.getLogger(ProductServiceImpl.class);
    @Autowired
    private ProductRepo pRepo;
    @Autowired
    private ParamUtil paramUtil;

    @Override
    public Product create(Product entty) throws SQLException {
        if (entty.getId() == null) {
            Integer createBy = paramUtil.getAccountId();
            entty.setCreatedBy(createBy);
            Product sanpham = pRepo.save(entty);

            return sanpham;
        }
        log.info("id product must not null");
        return null;
    }

    @Override
    public Product update(Product update) {
        if (update.getId() == null) {
            return null;
        }

        Integer updateBy = paramUtil.getAccountId();
        update.setUpdatedBy(updateBy);
        return pRepo.save(update);
    }

    @Override
    public Product Delete(Integer delete) {
        // nếu id có tồn tại thì set null
        if (delete != null && pRepo.existsById(delete)) {
            Product pro = pRepo.getOne(delete);
            pro.setDeleteAt(Instant.now());

            return pRepo.save(pro);
        }
        return null;
    }

    @Override
    public Product getbyID(Integer integer) {
        return pRepo.findById(integer).get();
    }

    @Override
    public List<Product> loadAll() {
        return pRepo.findAll();
    }

    @Override
    public Page<Product> loadPageActive(int page, int limit, String sortBy, String order) {
        log.debug("huy ddep trai");
        Sort.Direction direction = Sort.Direction.valueOf(order);
        Sort sort = Sort.by(direction, sortBy);
        Pageable paging = PageRequest.of(page, limit, sort);

        return pRepo.findAll(paging);
    }

    @Override
    public Page<Product> ProductDeleted(int page, int limit, String sortBy, String order) {
        Sort.Direction direction = Sort.Direction.valueOf(order);
        Sort sort = Sort.by(direction, sortBy);
        Pageable paging = PageRequest.of(page, limit, sort);

        return pRepo.findAll(paging);

    }

    @Override
    public Product getProductByid(Integer getId) {

        return pRepo.findById(getId).get();

    }

    @Override
    public List<Product> getListProductByCategori(Integer idCategori, int page, int limit, String sortBy, String orde) {
        Sort.Direction direction = Sort.Direction.valueOf(orde);
        Sort sort = Sort.by(direction, sortBy);
        Pageable paging = PageRequest.of(page, limit, sort);
//        Category cate = new Category();
//        cate.setId(idCategori);
        return pRepo.findAllByCategory(idCategori);
    }

    @Override
    public List<Product> getListProductByCategori(Integer idCategori) {
//        log.info(String.valueOf(idCategori));
//        Category cate = new Category();
        return pRepo.findAllByCategory(idCategori);
    }

    @Override
    public List<Product> getListTrendy() {
        return pRepo.getListProductTrendy();
    }

    @Override
    public int countAll() {
        return (int) pRepo.count();
    }
}

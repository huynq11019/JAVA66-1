package com.TTS.Repo.impl;

import com.TTS.DTO.ProductDTO;
import com.TTS.Entity.Product;
import com.TTS.Repo.CustomRepo.CustomProductRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

//@Transactional(readOnly = true)
public class CustomProductRepoImpl implements CustomProductRepo {
    @PersistenceContext
    private EntityManager entityManager;

//@Override
    public List<Product> layDanhSachActive(int page, int limit, String sortBy, boolean order) {
        StringBuilder hql =new StringBuilder();
        hql.append("SELECT p FROM Praoduct p WHERE p.deleteAt = null");
        Query query = entityManager.createQuery(hql.toString(), Product.class);
        query.setFirstResult(page*limit);
        query.setMaxResults(limit);
//        return query.getResultList();
    	List<Product> listpro = new ArrayList<>();
    	return listpro;
//        return null;
    }
//
//    @Override
    public List<ProductDTO> danhSachSanphamDaXoa(int page, int limit, String sortBy, boolean order) {
        StringBuilder hql = new StringBuilder();
        hql.append("SELECT p FROM Proaduct p WHERE p.id != null ");
        Query query = entityManager.createQuery(hql.toString(),Product.class);
        query.setFirstResult(page * limit);
        query.setMaxResults(limit);
        query.setParameter("orderby",sortBy);
        query.setParameter("order",(order? "desc": "asc"));
////        List<Product> list = query.getResultList();
//        return query.getResultList();
    	List<Product> listpro = new  ArrayList<>();

        return null;
    }
}

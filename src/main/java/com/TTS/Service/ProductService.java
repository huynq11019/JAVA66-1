package com.TTS.Service;

import com.TTS.Entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService extends AuditService<Product,Integer> {
    List<Product> loadAll();
    //load và phân trang những sản phẩm đang bán
    Page<Product> loadPageActive(int page, int limit, String sortBy, boolean order);
    //load và phân trang những sản phẩm đá xóa
    Page<Product> ProductDeleted (int page, int limit, String sortBy, boolean order);

    Product getProductByid(Integer getId);

    List<Product> getListProductByCategori (Integer idCategori,int page, int limit, String sortBy, boolean orde);

    List<Product> getListProductByCategori(Integer idCategori);

    int countAll();
}

package com.TTS.Service.ServiceImpl;

import com.TTS.Entity.Product;
import com.TTS.Service.ShopingCartItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@SessionScope
//@Scope( value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShopingCartimpl implements ShopingCartItem {
    Map<Integer, Product> map = new HashMap<>();
//	List<Product> list = new ArrayList<Product>();


    @Override
    public Product add(Product product) {
        if (map.containsKey(product.getId())) { // kiểm tra id có trong map không
//			System.out.println("casse 1"+ product.getId());
            Product pro = map.get(product.getId()); // lấy sản phẩm trong map
           product.setQuantity(pro.getQuantity() + 1);
            //put sản phẩm vào giỏ hàng
            map.put(product.getId(), product);
        } else {

            map.put(product.getId(), product);
        }
        return product;
    }

    @Override
    public void remove(Integer id) {
        if (map == null) {
            map = new HashMap<>();
        }
        if (map.containsKey(id)) {
//	            map.remove(productId);
            map.remove(id);
        }

    }

    @Override
    public Product update(Product product, int qty) {
        if (map.containsKey(product.getId())) {
            product.setQuantity(qty);
            map.put(product.getId(), product);
        }
        return product;
    }

    @Override
    public void clear() {
        map = new HashMap<Integer, Product>();

    }

    @Override
    public int getCount() {
        System.out.println("size");
        return map.size();
    }

    @Override
    public double getAmout() { //lấy tổng số tiền của giỏ hàng
        int count = 0;
        for (Map.Entry<Integer, Product> list : map.entrySet()) {
            count += list.getValue().getRealPrice() * list.getValue().getQuantity();
        }
        return count;
    }

    @Override
    public Collection<Product> getItems() {
//        if (map == null ){
//            map = new HashMap<Integer, Product>();
//        }
        map.values();
        return map.values();
    }

}

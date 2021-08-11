package com.TTS.Controller;

import com.TTS.Entity.Category;
import com.TTS.Entity.Product;
import com.TTS.Service.CategoriesService;
import com.TTS.Service.ProductService;
import com.TTS.Service.ShopingCartItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
public class CartController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ShopingCartItem shopingCartItem;
    @Autowired
    private CategoriesService categoriesService;
    @GetMapping("/cart")
    public String indexCart(Model model) {
        shopingCartItem.getItems();
        model.addAttribute("cart", shopingCartItem);

         return "views/cart";
    }

    @GetMapping("/cart/add/{idpro}")
    public String add(@PathVariable("idpro") Integer idpro) {
        System.out.println("sản phẩm vừa thêm vào: " + idpro);
        Product product = productService.getProductByid(idpro);
        product.setQuantity(1);
        shopingCartItem.add(product);
        System.out.println("giỏ hàng" + shopingCartItem.getItems());
        return "redirect:/cart";
    }
    @PostMapping("/cart/add/{idProduct}/{soluongSanPham}")
    public String addTocartwithQuantity(@PathVariable("idProduct") Integer idProduct,@PathVariable("soluongSanPham") Integer soluongSanPham){
        System.out.println("sản phẩm vừa thêm vào: " + idProduct);
        Product product = productService.getProductByid(idProduct);
        product.setQuantity(soluongSanPham);
        shopingCartItem.add(product);
        System.out.println("giỏ hàng" + shopingCartItem.getItems());
        return "redirect:/cart";
    }
    @GetMapping("/cart/remove/{id}")
    public String removeCart(@PathVariable("id") Integer idpro) {
        System.out.println("id muốn xóa: " + idpro);
        shopingCartItem.remove(idpro);
        return "redirect:/cart";
    }

    @GetMapping("/cart/clear")
    public String clearcart() {
        shopingCartItem.clear();
        return "redirect:/cart";
    }

    @GetMapping("/cart/update/{id}")
    public String updateCart(@PathVariable("id") Integer id, @RequestParam("qty") Integer qty) {
        Product p = productService.getProductByid(id);
        shopingCartItem.update(p, qty);
        return "redirect:/cart";
    }

    @ModelAttribute
    public void addattribute(Model model) {
        List<Category> listCate = categoriesService.loadAll();
        model.addAttribute("activeTab", "home");
        model.addAttribute("cate", listCate);
    }
}

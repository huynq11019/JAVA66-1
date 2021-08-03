package com.TTS.Controller;

import com.TTS.Entity.Category;
import com.TTS.Entity.Product;
import com.TTS.Service.CategoriesService;
import com.TTS.Service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
@Slf4j
public class HomeController {
    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private ProductService productService;

    @GetMapping({"/home", "/"})
    public String index(Model model) {
        // sản phẩm mới về
        Page<Product> productsArrived = productService.loadPageActive(0, 8, "id", "DESC");
        // sản phẩm trendy
        List<Product> productsTrendy = productService.getListTrendy();
        model.addAttribute("productTrendy", productsTrendy);
        model.addAttribute("productArrived",productsArrived);
        return "views/index";
    }

    @ModelAttribute
    public void addattribute(Model model) {
        List<Category> listCate = categoriesService.loadAll();
        model.addAttribute("activeTab", "home");
        model.addAttribute("cate", listCate);
    }

}

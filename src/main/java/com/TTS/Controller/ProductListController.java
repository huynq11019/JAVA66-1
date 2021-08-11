package com.TTS.Controller;

import com.TTS.Entity.Category;
import com.TTS.Entity.Product;
import com.TTS.Service.CategoriesService;
import com.TTS.Service.ProductService;
import com.TTS.Util.SessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
public class ProductListController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private SessionService session;

    @GetMapping("/product")
    public String index(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(value = "limit", defaultValue = "10") int limit,
                        @RequestParam(value = "soryby", defaultValue = "id") String sortby, @RequestParam(value = "order", defaultValue = "DESC") String order) {
        //lấy danh sách sản phẩm
        Page<Product> listPro = productService.loadPageActive(page, limit, sortby, order);
        model.addAttribute("listP", listPro);
        return "views/product";
    }

    @GetMapping("/product/search")
    public String searchProduct(@RequestParam(name = "keyword") String keyword, Model model) {
        log.info("keywword search is:" + keyword);
        List<Product> listPro = productService.searchProduct(keyword);
//        Page<Product> listPro = productService.loadPageActive(0, 10 , "id", "ASC");
        model.addAttribute("listP", listPro);
        return "views/product";
    }

    // lấy sản phẩm theo danh mục
    @GetMapping("/{cateId}/produx")
    public String loadProductByid(@PathVariable("cateId") String cateId, Model modle) {
        try {
            Integer parse = Integer.parseInt(cateId);
            log.info(String.valueOf(parse));
            List<Product> loadByCate = productService.getListProductByCategori(parse);
            modle.addAttribute("listP", loadByCate);
            return "views/product";

        } catch (Exception ex) {
            log.error(ex.getMessage());
            return "redirect:/error";
        }
    }
    // tìm kiếm sản phẩm

    @ModelAttribute
    public void addTtribute(Model mode) {
        List<Category> listCate = categoriesService.loadAll();
        mode.addAttribute("cate", listCate);
        mode.addAttribute("activeTab", "produx");
    }
}

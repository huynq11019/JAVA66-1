package com.TTS.Rest;

//import org.apache.log4j.Logger;

import com.TTS.DTO.OutPut;
import com.TTS.DTO.ProductDTO;
import com.TTS.Entity.Product;
import com.TTS.Service.ProductService;
import com.TTS.maper.ProductMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@Slf4j
public class RestProduct {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductMapper mapper;

    @GetMapping("/api/products")
    public ResponseEntity<OutPut<ProductDTO>> getlistActive(@RequestParam(name = "page", defaultValue = "0") int page,
                                                            @RequestParam(name = "limit", defaultValue = "5") int limit,
                                                            @RequestParam(name = "sortby", defaultValue = "id") String sortBy,
                                                            @RequestParam(name = "order", defaultValue = "DESC") String order) {

        Page<Product> paging = productService.loadPageActive(page, limit, sortBy, order);
        if (paging != null) {
            int countAllProduct = productService.countAll();
            OutPut<ProductDTO> out = new OutPut<>();
//			log.debug("hello");
            out.setContent(mapper.toListDto(paging.getContent()));
            out.setOrder(order);
            out.setLimit(limit);
            out.setEmlementOfpage(paging.getNumberOfElements());
            out.setPage(paging.getNumber());
            out.setOrderBy(sortBy);
            out.setTotalElement(productService.countAll());
            out.setFrist((page <= 0 ? true : false));
            if (countAllProduct % limit == 0) {
                out.setTotalPage(countAllProduct / limit);
            } else {
                out.setTotalPage((countAllProduct / limit) + 1);
            }
            return ResponseEntity.ok(out);
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(null);
    }

    // load sản phẩm theo id
    @GetMapping("/api/product/{id}")
    public ResponseEntity<ProductDTO> productByCate(@PathVariable("id") Integer id) {
        Product pro = productService.getProductByid(id);
        ProductDTO productDTO = mapper.toDTO(pro);
        return ResponseEntity.ok(productDTO);
    }

    // load sản phẩm đã xóa
    @GetMapping("/api/deleted/product")
    public ResponseEntity<OutPut<Product>> loadAllDeleted(@RequestParam(name = "page", defaultValue = "0") int page,
                                                          @RequestParam(name = "limit", defaultValue = "10") int limit,
                                                          @RequestParam(name = "sortby", defaultValue = "id") String sortBy,
                                                          @RequestParam(name = "order", defaultValue = "DESC") String order) {
        Page<Product> paging = productService.loadPageActive(page, limit, sortBy, order);
        if (paging != null) {
            OutPut<Product> out = new OutPut<>();
//			log.debug("hello");
            out.setContent(paging.getContent());
            out.setOrder(order);
            out.setLimit(paging.getNumberOfElements());
            out.setPage(paging.getNumber());
            out.setOrderBy(sortBy);
            out.setFrist((page <= 0 ? true : false));
            out.setTotalPage((int) (paging.getTotalElements() / limit));
            return ResponseEntity.ok(out);
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(null);

    }

    // tạo sản phẩm
    @PostMapping("/api/product")
    public ResponseEntity<ProductDTO> createSanpham(@RequestBody ProductDTO newProduct) throws Exception {
//		try {
        log.info(newProduct.toString());
//		System.out.print(newProduct);
        Product pro = mapper.toEntity(newProduct);

        System.out.println(pro);
        try {

            Product proSaved = null;
            proSaved = productService.create(pro);
            return ResponseEntity.ok(mapper.toDTO(proSaved));
        } catch (Exception ex) {
            ex.printStackTrace();
//			log.error("đối tượng đã tồn tại trong hệ thống");
            throw new Exception("Đối tượng đã tồn tại trong hệ thống");
        }


    }

    @PutMapping("/api/product/{id}")
    public ResponseEntity<ProductDTO> updateProduk(@PathVariable("id") Integer idProduct, @RequestBody ProductDTO product) {
        product.setId(idProduct);
        Product pro = mapper.toEntity(product);
        Product productsaved = productService.update(pro);
        return ResponseEntity.ok(mapper.toDTO(productsaved));

    }

    // xóa sản phẩm
    @DeleteMapping("/api/product/{id}")
    public ResponseEntity<Boolean> removeR(@PathVariable("id") Integer idProduct) {
        try {
            productService.Delete(idProduct);
            log.info(String.valueOf(idProduct));
//		System.out.println(p);
            return ResponseEntity.ok(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            log.info("xóa Product Không thành công");
            return ResponseEntity.badRequest().body(false);
        }

    }


    @GetMapping("/api/{id}/prodcut")
    public ResponseEntity<List<ProductDTO>> getProductBycategory(@PathVariable("id") Integer idCategory) {
//TODO get danh sách sản phẩm theo categori
        List<Product> list = productService.getListProductByCategori(idCategory, 0, 10, "id", "ASC");
        List<ProductDTO> listDTO = mapper.toListDto(list);
        return ResponseEntity.ok(listDTO);
    }

}

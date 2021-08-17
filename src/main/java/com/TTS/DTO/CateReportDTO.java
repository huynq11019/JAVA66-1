package com.TTS.DTO;

import com.TTS.Entity.Category;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public class CateReportDTO {

    private Category category;
    private Long countProduct; // tổng số sảnphaarm
    private Long countOrder; // tổng số đơn hàng

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setCountProduct(Long countProduct) {
        this.countProduct = countProduct;
    }

    public void setCountOrder(Long countOrder) {
        this.countOrder = countOrder;
    }

    public Category getCategory() {
        Category category1 = new Category();
        category1.setId(this.category.getId());
        category1.setName(this.category.getName());
        return category1;
    }

    public Long getCountProduct() {
        return countProduct;
    }

    public Long getCountOrder() {
        return countOrder;
    }
}

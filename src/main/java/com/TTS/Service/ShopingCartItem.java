package com.TTS.Service;

import com.TTS.Entity.Product;

import java.util.Collection;

public interface ShopingCartItem {
    /**
     * Thêm mặt hàng vào giỏ hoặc tăng số lượng lên 1 nếu đã tồn tại
     *
     * @param id là mã mặt hàng cần thêm
     * @return mặt hàng đã được thêm vào hoặc cập nhật số lượng
     */
    Product add(Product product);

    /**
     * Xóa mặt hàng khỏi giỏ
     *
     * @param id mã mặt hàng cần xóa
     */
    void remove(Integer id);

    /**
     * Thay đổi số lượng lên của mặt hàng trong giỏ
     *
     * @param id  mã mặt hàng
     * @param qty số lượng mới
     * @return mặt hàng đã được thay đổi số lượng
     */
    Product update(Product product, int qty);

    /**
     * Xóa sạch các mặt hàng trong giỏ
     */
    void clear();

    /**
     * Lấy tất cả các mặt hàng trong giỏ
     */
    Collection<Product> getItems();

    /**
     * Lấy tổng số lượng các mặt hàng trong giỏ
     */
    int getCount();

    /*
     * lấy tổng só tiền trong giỏ
     */
    double getAmout();
}

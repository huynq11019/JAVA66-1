package com.TTS.Service.ServiceImpl;

import com.TTS.Entity.Account;
import com.TTS.Entity.Order;
import com.TTS.Entity.OrderDetail;
import com.TTS.Entity.Product;
import com.TTS.Repo.AccountRepo;
import com.TTS.Repo.OrderDetailRepo;
import com.TTS.Repo.OrderRepo;
import com.TTS.Service.OrderService;
import com.TTS.Service.ShopingCartItem;
import com.TTS.sercurity.CustomUserDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private OrderDetailRepo orderDetailRepo;
    @Autowired
    private ShopingCartItem shopingCartItem;

    @Override
    public Order create(Order entty) throws SQLException {

        if (entty.getId() == null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetail customer = (CustomUserDetail) authentication.getPrincipal();
            Account acc = new Account();
            acc.setId(customer.getIdAccount());
            entty.setAccountod(acc);
            entty.setStatus(0);

//            Set<OrderDetail> orderDetails = toOrderDetail(shopingCartItem.getItems(), entty);
//            System.out.println(orderDetails);
//            Collection<Product> listCart = shopingCartItem.getItems();
//            listCart.forEach(item -> {
//                OrderDetail orderDetail = new OrderDetail();
//                orderDetail.setFromOd(entty);
//                orderDetail.setQuantity(item.getQuantity());
//                orderDetail.setFromProduct(new Product(item.getId()));
//                orderDetail.setPrice((item.getRealPrice() - ((item.getRealPrice() * item.getDiscount()) / 100)) * item.getQuantity());
//                entty.getOrderDetail().add(orderDetail);
//            });
            log.info(entty.toString());
//       entty.setOrderDetail(orderDetails);
            Order odServed = orderRepo.save(entty);
                 toOrderDetail(shopingCartItem.getItems(),odServed);

            return odServed;
        }
//        return orderRepo.save(entty);
        return null;
    }


    private Set<OrderDetail> toOrderDetail(Collection<Product> cartItems, Order od) {
        Set<OrderDetail> listOrderDetail = new HashSet<>();
        cartItems.forEach(e -> {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setFromOd(od);
            orderDetail.setQuantity(e.getQuantity());
            orderDetail.setFromProduct(new Product(e.getId()));
            orderDetail.setPrice((e.getRealPrice() - ((e.getRealPrice() * e.getDiscount()) / 100)) * e.getQuantity());
            orderDetailRepo.save(orderDetail);
            listOrderDetail.add(orderDetail);
        });

        return listOrderDetail;
    }
//    private Iterable<OrderDetail> to0derdetail(Collection<Product> cartItems, Order od){
//        Iterable<OrderDetail> listOderDetail = new HashSet<>();
//        cartItems.forEach(item -> {
//            OrderDetail orderDetail = new OrderDetail();
//            orderDetail.setFromOd(od);
//            orderDetail.setQuantity(item.getQuantity());
//            orderDetail.setFromProduct(new Product(item.getId()));
//            orderDetail.setPrice((item.getRealPrice() - ((item.getRealPrice() * item.getDiscount()) / 100)) * item.getQuantity());
//
//        });
//        return null;
//    }
    @Override
    public Order update(Order update) {
        if (update.getId() == null) {
            return null;
        }

        return orderRepo.save(update);
    }

    @Override
    public Order Delete(Integer delete) {
        try {

            Order od = orderRepo.getOne(delete);
            orderRepo.delete(od);
            return od;

        } catch (Exception ex) {
            log.info(ex.getMessage());
            throw new RuntimeException();
        }

    }

    @Override
    public Order getbyID(Integer integer) {

        return orderRepo.findById(integer).get();

    }

    @Override
    public List<Order> getOrderByUser(Integer idUser) {
//        Account acc = accountRepo.getOne(idUser);
        return orderRepo.findAllByAccountod_Id(idUser);

    }

    @Override
    public List<Order> getListOrderByStatus(Integer status) {
        return orderRepo.findAllByStatus(status);
    }

    @Override
    public Page<Order> getPage(int page, int limit, String sortBy, String order) {
        Sort.Direction direction = Sort.Direction.valueOf(order);
        Sort sort = Sort.by(direction, sortBy);
        Pageable paging = PageRequest.of(page, limit, sort);
        return orderRepo.findAll(paging);
    }

//    public Page<Order> getPage(int page, int limit, String sortBy, String order) {
//        Sort.Direction direction = Sort.Direction.valueOf(order);
//        Sort sort = Sort.by(direction, sortBy);
//        Pageable paging = PageRequest.of(page, limit, sort);
//        return orderRepo.findAll(paging);
//
//
//    }
}

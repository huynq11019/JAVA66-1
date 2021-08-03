package com.TTS.Service.ServiceImpl;

import com.TTS.Entity.Account;
import com.TTS.Entity.Order;
import com.TTS.Repo.AccountRepo;
import com.TTS.Repo.OrderRepo;
import com.TTS.Service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private AccountRepo accountRepo;

    @Override
    public Order create(Order entty) throws SQLException {
        if (entty.getId() == null) {
            return null;
        }
        return orderRepo.save(entty);
    }

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
        Account acc = accountRepo.getOne(idUser);
        return orderRepo.findAllByAccount_od(acc);

    }

    @Override
    public List<Order> getListOrderByStatus(Integer status) {
        return orderRepo.findAllByStatus(status);
    }

    public Page<Order> getPage(int page, int limit, String sortBy, String order) {
        Sort.Direction direction = Sort.Direction.valueOf(order);
        Sort sort = Sort.by(direction, sortBy);
        Pageable paging = PageRequest.of(page, limit, sort);
        return orderRepo.findAll(paging);


    }
}

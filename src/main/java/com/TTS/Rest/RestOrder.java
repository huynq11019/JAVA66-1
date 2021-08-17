package com.TTS.Rest;

import com.TTS.DTO.OrderDTO;
import com.TTS.DTO.OutPut;
import com.TTS.Entity.Order;
import com.TTS.Service.OrderService;
import com.TTS.maper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
@CrossOrigin("*")
public class RestOrder {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderMapper orderMapper;

    @GetMapping("/admin/orders")
    public OutPut<Order> getPage(@RequestParam(name = "page", defaultValue = "0") int page,
                                 @RequestParam(name = "limit", defaultValue = "5") int limit,
                                 @RequestParam(name = "sortby", defaultValue = "id") String sortBy,
                                 @RequestParam(name = "order", defaultValue = "DESC") String order) {
        OutPut<Order> out = new OutPut<>();
        Page<Order> pageOrder = orderService.getPage(page, limit, sortBy, order);
        out.setOrderBy(sortBy);
        out.setLimit(limit);
        out.setPage(page);
        out.setTotalPage(pageOrder.getTotalPages());
        out.setTotalElement(pageOrder.getTotalElements()
        );
        List<Order> listOd = pageOrder.getContent();
        List<OrderDTO> listDto = orderMapper.toDto(listOd);
//        System.out.println(listDto);
        out.setContent(listOd);
        return out;
    }

    @PutMapping("orderstt/{id}")
    public ResponseEntity<Boolean> updateStatus(@PathVariable("id") Integer idOder, @RequestParam(name = "status") int status) {
        log.info("trạng thái mới của đơn hàng" + status);
        try {
            orderService.updateStatus(idOder, status);
            return ResponseEntity.ok(true);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new RuntimeException();
        }
    }

    @GetMapping("/orderetail/{id}")
    public ResponseEntity<Order> getOrderDetail(@PathVariable("id") Integer idOrder) {
        return ResponseEntity.ok(orderService.getbyID(idOrder));
    }

}

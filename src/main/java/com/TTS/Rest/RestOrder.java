package com.TTS.Rest;

import com.TTS.DTO.OrderDTO;
import com.TTS.DTO.OutPut;
import com.TTS.Entity.Order;
import com.TTS.Service.OrderService;
import com.TTS.maper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
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
        OutPut<Order> out  = new OutPut<>();
        Page<Order> pageOrder =  orderService.getPage(page, limit, sortBy, order);
        out.setOrderBy(sortBy);
        out.setLimit(limit);
        out.setPage(page);
        out.setTotalPage(pageOrder.getTotalPages());
        out.setTotalElement(pageOrder.getTotalElements()
        );
        List<Order> listOd =  pageOrder.getContent();
        List<OrderDTO> listDto = orderMapper.toDto(listOd);
//        System.out.println(listDto);
        out.setContent(listOd);
        return out;
    }

    @PutMapping("/api/orderstt/{id}")
    public ResponseEntity<Boolean> updateStatus(@PathVariable("id") Integer idOder, @RequestParam Boolean status ){
        return null;
    }

    public ResponseEntity<OrderDTO> getOrderDetail(){

        return null;
    }

}

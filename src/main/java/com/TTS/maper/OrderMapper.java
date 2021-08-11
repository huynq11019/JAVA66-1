package com.TTS.maper;

import com.TTS.DTO.OrderDTO;
import com.TTS.Entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderMapper  extends EntityMaper<OrderDTO, Order> {

   default List<Order> toEntity(List<OrderDTO> dtoList){

    return null;
   };

    @Mappings({@Mapping(target = "accIdString", source = "entity.accountod.id"),
    @Mapping(source = "entity.accountod.fullName", target = "accNameString")})
    OrderDTO toDto(Order entity);

   default List<OrderDTO> toDto(List<Order> entityList){
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    };
    Order toEntity(OrderDTO dto);
}

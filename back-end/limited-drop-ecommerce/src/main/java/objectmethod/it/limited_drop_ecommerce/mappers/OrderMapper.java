package objectmethod.it.limited_drop_ecommerce.mappers;

import objectmethod.it.limited_drop_ecommerce.dtos.model.OrderDto;
import objectmethod.it.limited_drop_ecommerce.entities.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toOrder(OrderDto orderDto);
    OrderDto toOrderDto(Order order);
    List <OrderDto> toListOrderDto(List<Order> Orders);
    List<Order> toListOrder(List<OrderDto> OrdersDto);
}

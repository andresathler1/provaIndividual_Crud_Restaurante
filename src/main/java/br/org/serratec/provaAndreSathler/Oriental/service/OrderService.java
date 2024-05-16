package br.org.serratec.provaAndreSathler.Oriental.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import br.org.serratec.provaAndreSathler.Oriental.dto.OrderDto;
import br.org.serratec.provaAndreSathler.Oriental.model.Order;
import br.org.serratec.provaAndreSathler.Oriental.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	public OrderRepository orderRepository;

	public List<OrderDto> listAll() {
		return orderRepository.findAll().stream()
		.map(l -> new OrderDto(l.getId(), l.getOrders(), l.getOrderFoodChoose(),
		l.getOrderPrice(), l.getClientName())).toList();		
	}

	public OrderDto makeOrder(OrderDto order) {
		Order orderEntity = orderRepository.save(order.toEntity());
		return orderEntity.toDto();
	}

	public Optional<OrderDto> alterOrder(Long id, OrderDto order) {
		Order orderEntity = order.toEntity();
		
		if(orderRepository.existsById(id)){
			orderEntity.setId(id);
			orderRepository.save(orderEntity);
			return Optional.of(orderEntity.toDto());			
		}
		return Optional.empty();
	}

	public boolean deleteOrder(Long id) {
		if(!orderRepository.existsById(id)) {
			return false;
		}
		orderRepository.deleteById(id);
		return true;
	}

	public List<OrderDto> getByName(String clientName) {
		return orderRepository.findByClientNameContainingIgnoreCase(clientName)
		.stream()
		.map(c -> new OrderDto(c.getId(), c.getOrders(), c.getOrderFoodChoose(),
		c.getOrderPrice(), c.getClientName())).toList();
	}

	public List<OrderDto> getByTable(String orders) {
		return orderRepository.findByOrdersContainingIgnoreCase(orders)
		.stream()
		.map(o -> new OrderDto(o.getId(), o.getOrders(), o.getOrderFoodChoose(),
		o.getOrderPrice(), o.getClientName())).toList();
	}

	public List<OrderDto> getByFood(String orderFoodChoose) {
		return orderRepository.findByOrderFoodChooseContainingIgnoreCase(orderFoodChoose)
		.stream()
		.map(of -> new OrderDto(of.getId(), of.getOrders(), 
		of.getOrderFoodChoose(), of.getOrderPrice(), of.getClientName())).toList();
	}

	public List<OrderDto> getByAscedent() {
		return orderRepository.findAllByOrderByOrderPriceAsc()
				.stream()
				.map(a -> new OrderDto(a.getId(),a.getOrders(), 
				a.getOrderFoodChoose(), a.getOrderPrice(), a.getClientName())).toList();
	}
	
}

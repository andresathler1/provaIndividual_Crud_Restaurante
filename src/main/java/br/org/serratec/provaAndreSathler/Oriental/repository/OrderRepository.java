package br.org.serratec.provaAndreSathler.Oriental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.provaAndreSathler.Oriental.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
	List<Order> findByClientNameContainingIgnoreCase(String clientName);
	List<Order> findByOrdersContainingIgnoreCase(String clientName);
	List<Order> findByOrderFoodChooseContainingIgnoreCase(String foodChoose);
	List<Order> findAllByOrderByOrderPriceAsc();
}

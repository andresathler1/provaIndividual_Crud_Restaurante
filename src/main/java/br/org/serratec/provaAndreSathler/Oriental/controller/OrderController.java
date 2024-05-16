package br.org.serratec.provaAndreSathler.Oriental.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.provaAndreSathler.Oriental.dto.OrderDto;
import br.org.serratec.provaAndreSathler.Oriental.service.OrderService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/{orders}")
public class OrderController {
	
	@Autowired
	private OrderService service;
	
	@GetMapping
	public ResponseEntity <List<OrderDto>> list(){
		return ResponseEntity.ok(service.listAll());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrderDto makeRestaurantOrder(@Valid @RequestBody OrderDto order) {
		return service.makeOrder(order);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<OrderDto> alterRestaurantOrder(@PathVariable Long id, @RequestBody OrderDto alteredOrder){
		Optional<OrderDto> order = service.alterOrder(id, alteredOrder);
		
		if(order.isPresent()) {
			return ResponseEntity.ok(order.get());
		}
			return ResponseEntity.notFound().build();
	}	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRestaurantOrder(@PathVariable Long id){
		if(!service.deleteOrder(id)) {
			return ResponseEntity.notFound().build();
		}
			return ResponseEntity.noContent().build();
	}
	

	@GetMapping("/nome_cliente")
	public ResponseEntity <List<OrderDto>> getOrderByName (@RequestBody String clientName){
		return ResponseEntity.ok(service.getByName(clientName));
	}
	
	@GetMapping("/mesa")
	public ResponseEntity <List<OrderDto>> getOrderByTable (@RequestBody String orders){
		return ResponseEntity.ok(service.getByTable(orders));
	}
	
	@GetMapping("/comida")
	public ResponseEntity <List<OrderDto>> getOrderByFood (@RequestBody String foodChoose){
		return ResponseEntity.ok(service.getByFood(foodChoose));
	}
	
	@GetMapping("/crescente")
	public ResponseEntity <List<OrderDto>> getOrderByAscedentPrice(){
		return ResponseEntity.ok(service.getByAscedent());
	}
		
}

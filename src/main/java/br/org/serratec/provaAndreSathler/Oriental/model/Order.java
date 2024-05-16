package br.org.serratec.provaAndreSathler.Oriental.model;

import br.org.serratec.provaAndreSathler.Oriental.dto.OrderDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String orders;
	private String orderFoodChoose;
	private Double orderPrice;
	private String clientName;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrders() {
		return orders;
	}
	public void setOrders(String orders) {
		this.orders = orders;
	}
	public String getOrderFoodChoose() {
		return orderFoodChoose;
	}
	public void setOrderFoodChoose(String orderFoodChoose) {
		this.orderFoodChoose = orderFoodChoose;
	}
	public double getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	public Order() {}
	public Order(Long id, String orders, String orderFoodChoose, double orderPrice, String clientName) {
		super();
		this.id = id;
		this.orders = orders;
		this.orderFoodChoose = orderFoodChoose;
		this.orderPrice = orderPrice;
		this.clientName = clientName;
	}
	
	public OrderDto toDto() {
		return new OrderDto(this.id, this.orders, this.orderFoodChoose, this.orderPrice, this.clientName);
	}
	
}

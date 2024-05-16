package br.org.serratec.provaAndreSathler.Oriental.dto;

import br.org.serratec.provaAndreSathler.Oriental.model.Order;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OrderDto(
		
		Long id,
		@NotBlank(message="Campo ordem não pode estar vazio.")
		String orders,
		@NotBlank(message="Campo tipo de comida não pode ser vazio.")
		String orderFoodChoose,
		@NotNull(message="Campo valor não pode estar vazio.")
		Double price,
		@NotBlank(message="Campo deve ter o nome do cliente!")
		String clientName
		) {

	public Order toEntity() {
		return new Order(this.id, this.orders, this.orderFoodChoose, this.price, this.clientName);
	}
}

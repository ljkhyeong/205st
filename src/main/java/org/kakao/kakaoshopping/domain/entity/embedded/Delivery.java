package org.kakao.kakaoshopping.domain.entity.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {

	@Embedded
	private Address address;

	@Embedded
	private PhoneNumber phoneNumber;

	@Column(columnDefinition = "VARCHAR(100) DEFAULT ''", nullable = false)
	private String deliveryRequest;


}

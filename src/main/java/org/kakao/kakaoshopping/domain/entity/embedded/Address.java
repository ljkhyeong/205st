package org.kakao.kakaoshopping.domain.entity.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

	@Column(columnDefinition = "VARCHAR(10) DEFAULT ''", nullable = false)
	private String zipCode;

	@Column(columnDefinition = "VARCHAR(50) DEFAULT ''", nullable = false)
	private String city;

	@Column(columnDefinition = "VARCHAR(50) DEFAULT ''", nullable = false)
	private String street;

}

package org.kakao.kakaoshopping.web.dto.item.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemSimpleView {
    private String name;
    private BigDecimal price;
    private String imagePath;
}

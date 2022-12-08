package com.myshop.catalog.domain.product;

import com.myshop.common.model.Money;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    @Test
    @DisplayName("상품 저장 테스트")
    void saveProductTest() {
        Product product = Product.builder()
                .name("테스트 상품")
                .price(new Money(1000))
                .detail("테스트 상품상세")
                .sellStatus(ProductSellStatus.SELL)
                .stockNumber(30).build();

        Product savedProduct = productRepository.save(product);
        Assertions.assertThat(savedProduct).isNotNull();
    }

}
package com.myshop.catalog.domain.product;

import com.myshop.common.model.Money;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static com.myshop.catalog.domain.product.QProduct.product;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;
    @PersistenceContext
    EntityManager em;

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
        assertThat(savedProduct).isNotNull();
    }

    @Test
    @DisplayName("상품명 또는 상품상세설명 OR 테스트")
    void findByNameOrDetailTest() {
        this.createProductList();
        List<Product> result = productRepository.findByNameLikeOrDetailLike("%상품 3%", "%상세 7%");
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("특정 가격 이하의 상품 내림차순 조회 테스트")
    void findByPriceLessThanOrderByPriceDesc() {
        this.createProductList();
        int price = 1005;
        List<Product> result = productRepository.findByPriceLessThanEqualOrderByPriceDesc(new Money(price));
        assertThat(result.size()).isEqualTo(5);
        assertThat(result.get(0).getPrice()).isEqualTo(new Money(1005));
    }

    @Test
    @DisplayName("JPQL 로 상품 상세 조회")
    void findByDetail_JPQL() {
        this.createProductList();
        List<Product> result = productRepository.findByDetail("상품상세");
        assertThat(result.size()).isEqualTo(10);
    }

    @Test
    @DisplayName("nativeQuery 속성을 이용한 상품 상세 조회")
    void findByDetail_Native() {
        this.createProductList();
        List<Product> result = productRepository.findByDetail_Native("상품상세");
        assertThat(result.size()).isEqualTo(10);
    }

    @Test
    @DisplayName("QueryDSL 조회테스트")
    void queryDslTest() {
        createProductList();
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        List<Product> result = queryFactory
                .selectFrom(product)
                .where(product.sellStatus.eq(ProductSellStatus.SELL))
                .orderBy(product.price.desc()).fetch();
        assertThat(result.size()).isEqualTo(5);
    }

    @Test
    @DisplayName("SELL 중인 상품만 조회 테스트 - queryDsl")
    void getOnly_SELL_Product() {
        createProductList();

        String detail = "상품상세";
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(product.sellStatus.eq(ProductSellStatus.SELL));
        booleanBuilder.and(product.detail.like("%" + detail + "%"));

        PageRequest pageable = PageRequest.of(0, 5);
        Page<Product> result = productRepository.findAll(booleanBuilder, pageable);

        assertThat(result.getContent().size()).isEqualTo(5);
    }


    private void createProductList() {
        for (int i = 1; i <= 10; i++) {
            int value = 1000 + i;
            Product newProduct = null;
            if (i % 2 == 0) {
                newProduct = Product.builder()
                        .name("테스트 상품 " + i)
                        .price(new Money(value))
                        .detail("테스트 상품상세 " + i)
                        .sellStatus(ProductSellStatus.SELL)
                        .stockNumber(30).build();
            } else {
                newProduct = Product.builder()
                        .name("테스트 상품 " + i)
                        .price(new Money(value))
                        .detail("테스트 상품상세 " + i)
                        .sellStatus(ProductSellStatus.SOLD_OUT)
                        .stockNumber(0).build();
            }

            productRepository.save(newProduct);
        }
    }

}
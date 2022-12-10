package com.myshop.catalog.domain.product;

import com.myshop.common.model.Money;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<List<Product>> findByNameContaining(String name);
    List<Product> findByNameLikeOrDetailLike(String name, String detail);

    List<Product> findByPriceLessThanEqualOrderByPriceDesc(Money price);

    @Query("select p from Product p where p.detail like %:detail% order by p.price desc")
    List<Product> findByDetail(String detail);

    @Query(value = "select * from product p where p.detail like %:detail% order by p.price desc", nativeQuery = true)
    List<Product> findByDetail_Native(String detail);
}

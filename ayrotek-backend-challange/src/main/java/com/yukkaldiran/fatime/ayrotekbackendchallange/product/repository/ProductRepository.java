package com.yukkaldiran.fatime.ayrotekbackendchallange.product.repository;

import com.yukkaldiran.fatime.ayrotekbackendchallange.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}

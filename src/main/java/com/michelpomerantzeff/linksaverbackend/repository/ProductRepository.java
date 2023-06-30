package com.michelpomerantzeff.linksaverbackend.repository;

import com.michelpomerantzeff.linksaverbackend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

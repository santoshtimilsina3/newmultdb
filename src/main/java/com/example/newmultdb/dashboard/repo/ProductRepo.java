package com.example.newmultdb.dashboard.repo;

import com.example.newmultdb.dashboard.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
    Product findByTitle(String title);
}

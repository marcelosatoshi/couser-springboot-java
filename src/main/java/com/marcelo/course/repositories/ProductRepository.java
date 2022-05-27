package com.marcelo.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcelo.course.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}

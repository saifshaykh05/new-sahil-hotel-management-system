package com.newsahilhotel.repository;

import com.newsahilhotel.entity.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory,Long> {
    boolean existsByName(String name);
    List<FoodCategory> findByActiveTrue();
    Optional<FoodCategory> findByName(String name);
    List<FoodCategory> findByActive(Boolean active);
}

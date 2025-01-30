package com.weclear.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import com.weclear.backend.model.*;


public interface WorkRepository extends JpaRepository<Work, Long> {

   // List<Work> findByCategoryAndSubCategory(Category.WorkCategory workCategory, Category.WorkSubCategory workSubCategory);

    //List<Work> findByGroupAndCategory(String group, Category.WorkSubCategory workSubCategory);
    List<Work> findByWorkCategoryAndWorkSubCategory(Category.WorkCategory workCategory, Category.WorkSubCategory workSubCategory);

    
}

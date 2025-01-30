package com.weclear.backend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.weclear.backend.repository.WorkRepository;


import com.weclear.backend.model.Category;

import com.weclear.backend.model.Work;

import java.util.List;
import java.util.Optional;

@Service
public class WorkService {
    
    @Autowired
    WorkRepository workRepository;

    public List<Work> getALlWorks() {
        return workRepository.findAll();
    }
    
    public Optional<Work> getWorkById(Long id) {
        return workRepository.findById(id);
    }

    public List<Work> getWorksBySubCategory(Category.WorkCategory category, Category.WorkSubCategory subCategory) {
        return workRepository.findByWorkCategoryAndWorkSubCategory(category, subCategory);
    }


    public Work saveWork(Work work) {
        return workRepository.save(work);
    }
    
    public void deleteWork(Long id) {
        workRepository.deleteById(id);
    }
}

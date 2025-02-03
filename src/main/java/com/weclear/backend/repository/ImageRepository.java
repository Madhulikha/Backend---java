package com.weclear.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weclear.backend.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}

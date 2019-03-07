package com.ps.imgclassifier.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ps.imgclassifier.model.Image;

public interface ImageRepo extends JpaRepository<Image, Long>{

}

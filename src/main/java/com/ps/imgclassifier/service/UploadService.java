package com.ps.imgclassifier.service;

import org.springframework.stereotype.Service;

import com.ps.imgclassifier.model.Image;

@Service
public interface UploadService {
    void save(Image img);
    Image getOne(Long id);
}

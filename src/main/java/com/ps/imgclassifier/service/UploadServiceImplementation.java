package com.ps.imgclassifier.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ps.imgclassifier.model.Image;
import com.ps.imgclassifier.repository.ImageRepo;

@Repository
public class UploadServiceImplementation implements UploadService {
	
	private final ImageRepo imgRepo;

	@Autowired
	public UploadServiceImplementation(ImageRepo imgRepo) {
		this.imgRepo = imgRepo;
	}
	
	@Override
	@Transactional
	public void save(Image img) {
		imgRepo.save(img);
	}

	@Override
	public Image getOne(Long id) {	
		return imgRepo.getOne(id);
	}


}

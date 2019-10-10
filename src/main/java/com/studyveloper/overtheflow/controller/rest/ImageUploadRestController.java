package com.studyveloper.overtheflow.controller.rest;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.studyveloper.overtheflow.exception.ImageException;
import com.studyveloper.overtheflow.service.ImageService;
import com.studyveloper.overtheflow.service.ImageService.ImageType;

@RestController
@RequestMapping(value = "/api")
public class ImageUploadRestController {

	private static final Logger logger = LoggerFactory.getLogger(ImageUploadRestController.class);

	@Autowired
	ImageService imageService;

	@RequestMapping(value = "/imageupload", method = RequestMethod.POST)
	public String imageUpload(String tempImageName, MultipartFile imageData) {
		try {
			return imageService.uploadTempImage(tempImageName, imageData.getBytes());
		} catch (ImageException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/commit", method = RequestMethod.GET)
	public void commit(String tempImageName) {
		try {
			logger.info(tempImageName);
			imageService.commitTempImage(tempImageName, "1", "1", ImageService.ImageType.PROFILE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/display/img", method = RequestMethod.GET)
	public byte[] displayImg(Model model) {
		try {
			return imageService.getImage("1", ImageType.PROFILE, "1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

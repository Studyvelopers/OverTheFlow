package com.studyveloper.overtheflow.service;

import java.io.File;
import java.util.StringTokenizer;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.studyveloper.overtheflow.controller.rest.ImageUploadRestController;
import com.studyveloper.overtheflow.exception.ImageException;
import com.studyveloper.overtheflow.util.FilePathUtil;

@Service
public class ImageServiceImpl implements ImageService{
	private static final Logger logger = LoggerFactory.getLogger(ImageUploadRestController.class);
	@Override
	public String uploadTempImage(String tempImageName, byte[] imageData) throws ImageException {
		
		if (imageData == null) {
			throw new ImageException("이미지 데이터가 없습니다.", ImageException.ErrorCode.NULL_PARAMETER);
		}
		
		tempImageName = UUID.randomUUID().toString()+"."+tempImageName;
		
		// 임시 폴더 경로 받아오기
		String tempPath = FilePathUtil.getTempFilePath();
		
		// 임시 폴더 생성
		File dir = new File(tempPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		// 저장 파일 생성
		File tempFile = new File(tempPath,tempImageName);
		logger.info(tempImageName);
		// 파일에 데이터 쓰기
		try {
			FileCopyUtils.copy(imageData, tempFile);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ImageException(e.getMessage(), ImageException.ErrorCode.UNKNOWN);
		}
		
		return tempImageName;
	}

	@Override
	public void commitTempImage(String tempImageName, String id, String imageName, ImageType imageType)
			throws ImageException {
		// TODO Auto-generated method stub
		if (tempImageName == null || id == null || imageName == null || imageType == null) {
			throw new ImageException("요청에 필요한 전달인자가 부족합니다.", ImageException.ErrorCode.NULL_PARAMETER);
		}
		
		if (imageType.getValue() == ImageType.TEMP.getValue()) {
			throw new ImageException("임시 폴더에 업로드가 불가능합니다.", ImageException.ErrorCode.UNKNOWN);
		}
		
		// 이미지 가져오기
		File tempFile = new File(FilePathUtil.getTempFilePath() + File.separator + tempImageName);
		if (!tempFile.exists()) {
			throw new ImageException("이미지가 존재하지 않습니다.", ImageException.ErrorCode.NOT_EXISTS);
		}
		
		// 폴더 생성
		File dir = new File(getImagePath(id, imageType));
		if (!dir.exists()) {
			dir.mkdirs();
		}
		// 파일 생성하기
		File imageFile = new File(dir.getPath() + File.separator + imageName);
		
		try {
			FileCopyUtils.copy(tempFile, imageFile);
			tempFile.delete();
		} catch (Exception ex) {
			throw new ImageException(ex.getMessage(), ImageException.ErrorCode.UNKNOWN);
		}
	}

	@Override
	public void deleteTempImage(String tempImageName) throws ImageException {
		// TODO Auto-generated method stub
		if (tempImageName == null) {
			throw new ImageException("요청에 필요한 전달인자가 부족합니다.", ImageException.ErrorCode.NULL_PARAMETER);
		}
		
		// 이미지 가져오기
		File tempFile = new File(FilePathUtil.getTempFilePath() + File.separator + tempImageName);
		if (!tempFile.exists()) {
			throw new ImageException("이미지가 존재하지 않습니다.", ImageException.ErrorCode.NOT_EXISTS);
		}
		
		tempFile.delete();
	}

	@Override
	public void deleteImage(String id, String imageName, ImageType imageType) throws ImageException {
		// TODO Auto-generated method stub
		if (id == null || imageName == null || imageType == null) {
			throw new ImageException("요청에 필요한 전달인자가 부족합니다.", ImageException.ErrorCode.NULL_PARAMETER);
		}
		
		if (imageType.getValue() == ImageType.TEMP.getValue()) {
			return;
		}
		
		// 이미지 파일 가져오기
		File dir = new File(getImagePath(id, imageType));
		if (!dir.exists()) {
			throw new ImageException("경로가 존재하지 않습니다.", ImageException.ErrorCode.NOT_EXISTS);
		}
	
		File image = new File(dir.getPath() + File.separator + imageName);
		if (!image.exists()) {
			throw new ImageException("파일이 존재하지 않습니다.", ImageException.ErrorCode.NOT_EXISTS);
		}
		image.delete();
	}
	
	@Override
	public void deleteImages(String id, String[] imageNames, ImageType imageType) throws ImageException {
		for (String imageName : imageNames) {
			deleteImage(id, imageName, imageType);
		}
	}

	@Override
	public byte[] getImage(String imageName, ImageType imageType, String id) throws ImageException {
		if (imageName == null || imageType == null) {
			throw new ImageException("요청에 필요한 전달인자가 부족합니다.", ImageException.ErrorCode.NULL_PARAMETER);
		}
		
		String filePath = getImagePath(id, imageType);
		// 이미지 파일 불러오기
		
		File image = new File(filePath, imageName);
		
		logger.info(image.getPath());
		
		// 이미지 파일 확인
		if (!image.exists()) {
			image = new File(getImagePath(null, ImageType.TEMP), "basicImage.jpg");
		}
		
		// 이미지 데이터 복사
		byte[] imageData = null;
		try {
			imageData = FileCopyUtils.copyToByteArray(image);
		} catch(Exception e) {
			throw new ImageException(e.getMessage(), ImageException.ErrorCode.UNKNOWN);
		}
		
		return imageData;
	}
	
	
	
	
	private String getImagePath(String id, ImageType imageType) {
		String filePath = null;
		switch (imageType) {
		case TEMP:
			filePath = FilePathUtil.getTempFilePath();
			break;
		case MUSIC:
			filePath = FilePathUtil.getMusicImagePath(id);
			break;
		case PLAYLIST:
			filePath = FilePathUtil.getPlaylistImagePath(id);
			break;
		case PROFILE:
			filePath = FilePathUtil.getProfileImagePath(id);
			break;
		case PROFILE_BACKGROUND:
			filePath = FilePathUtil.getProfileBackgroundImagePath(id);
			break;
		}
		return filePath;
	}
}

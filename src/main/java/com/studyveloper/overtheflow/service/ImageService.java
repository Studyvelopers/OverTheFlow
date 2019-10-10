package com.studyveloper.overtheflow.service;

import com.studyveloper.overtheflow.exception.ImageException;
import com.studyveloper.overtheflow.service.ImageService.ImageType;

public interface ImageService {
	
	public enum ImageType {
		PROFILE(0),
		PROFILE_BACKGROUND(1),
		MUSIC(2),
		PLAYLIST(3),
		TEMP(4)
		;
		private int typeCode;
		ImageType(int typeCode) {
			this.typeCode = typeCode;
		}
		public int getValue() {
			return typeCode;
		}
	}
	
	/**
	 * 서버에 이미지를 업로드하기 위해 사용하는 메소드이다. 이미지를 업로드하면 서버에 바로 저장되는 것이 아니라 임시폴더에 저장된 후 
	 * 커밋 메소드를 호출하여 저장을 요청하여야 한다.
	 * @param tempImageName 임시로 저장할 이미지 이름이다. NULL 이라면 새로운 임시 이미지 이름을 생성한다.
	 * @param imageData 저장할 이미지 데이터이다.
	 * @return 이미지가 임시폴더에 저장되면 저장시 사용된 이미지 이름이 반환된다.
	 * @throws Exception
	 */
	public String uploadTempImage(String tempImageName, byte[] imageData) throws ImageException;
	
	/**
	 * 임시 폴더에 저장된 임시 이미지 파일을 실제 관리되는 이미지 폴더로 옮기는 작업을 한다.
	 * @param tempImageName 임시 폴더에 저장되어 있는 이미지 파일 이름이다.
	 * @param id 실제 관리될 폴더 경로를 알기위해 필요한 회원번호이다.
	 * @param imageName 실제로 저장될 이미지 파일 이름이다.
	 * @param imageType 이미지 종류를 나타내는 타입이다.
	 * @throws Exception
	 */
	public void commitTempImage(String tempImageName, String id, String imageName, ImageType imageType) throws ImageException;
	
	/**
	 * 임시 폴더에 저장된 이미지를 삭제하는 메소드이다.
	 * @param tempImageName 임시 폴더에 저장되어있는 이미지 파일의 이름이다.
	 * @throws TSOPException
	 */
	public void deleteTempImage(String tempImageName) throws ImageException;
	
	/**
	 * 사용자의 이미지 파일을 지우는 메소드이다. 회원번호와 이미지 종류, 이미지 이름을 주어 해당 파일을 삭제한다.
	 * @param id 회원번호
	 * @param ImageName 이미지 명
	 * @param imageType 이미지 종류
	 * @throws TSOPException
	 */
	public void deleteImage(String id, String ImageName, ImageType imageType) throws ImageException;
	
	/**
	 * 회원의 이미지를 여러개 지울 때 사용하는 메소드이다.
	 * @param id 회원번호
	 * @param imageNames 이미지 명들
	 * @param imageType 이미지 종류
	 * @throws TSOPException
	 */
	public void deleteImages(String id, String[] imageNames, ImageType imageType) throws ImageException; 
	
	/**
	 * 서버에 저장된 이미지 데이터를 가져오는 메소드이다.
	 * @param imageName 이미지 이름
	 * @param imageType 이미지 종류
	 * @param id 회원번호
	 * @return
	 * @throws TSOPException
	 */
	public byte[] getImage(String imageName, ImageType imageType, String id) throws ImageException;

}

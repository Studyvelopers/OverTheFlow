package com.studyveloper.overtheflow.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

public class IdentifierGenerator {
	public static String generateId(String key) {
		try {
			// 현재 시간을 키 값과 합치기
			String value = key + DateTimeFormatters.FULL_FORMATTER.format(LocalDateTime.now());

			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(value.getBytes());
			MessageDigest generator = (MessageDigest)md.clone();
			byte[] identifier = generator.digest();
			StringBuffer sb = new StringBuffer(); 
            for(int i=0; i<identifier.length; i++) {
                sb.append(Integer.toString((identifier[i]&0xff) + 0x100, 16).substring(1));
            }
 
            return sb.toString();
        } catch(NoSuchAlgorithmException e){
            e.printStackTrace();
            return "error";
		} catch (CloneNotSupportedException ex) {
			ex.printStackTrace();
			return "error";
		}
	}
}

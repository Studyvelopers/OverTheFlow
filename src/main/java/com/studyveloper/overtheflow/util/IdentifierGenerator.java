package com.studyveloper.overtheflow.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class IdentifierGenerator {
	public static String generateId(Integer key) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(key.toString().getBytes());
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

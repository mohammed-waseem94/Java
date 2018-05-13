package application;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import res.Values;

public class Encryption {

	public static String encryptpwd(String pwd) {
		 String passwordToHash = pwd;
	        String generatedPassword = null;
	        try {
	            MessageDigest md = MessageDigest.getInstance(Values.encalgo);
	            md.update(passwordToHash.getBytes());
	            byte[] bytes = md.digest();
	            StringBuilder sb = new StringBuilder();
	            for(int i=0; i< bytes.length ;i++)
	            {
	                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	            }
	            generatedPassword = sb.toString();
	            return generatedPassword;
	        }
	        catch (NoSuchAlgorithmException e)
	        {			
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Exception Dialog");
				alert.setHeaderText("");
				alert.setContentText(e.getMessage());				
	            e.printStackTrace();
	        }			
		return null;
	}
}

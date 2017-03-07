package net.hdcx.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

/**
 * 密码验证器
 * Created by Kevin on 2017/3/2.
 */
public class PasswordValidator {
	public boolean validate(char[] password){
		boolean isTrue = false;
		String defaultPassword = getDefaultPassword();
		if (Arrays.equals(password, defaultPassword.toCharArray())){
			isTrue = true;
		}
		return isTrue;
	}

	private String getDefaultPassword(){
		String defaultPassword = null;
		Properties p = new Properties();
		try {
			InputStream is = new FileInputStream("res/properties/admin.properties");
			p.load(is);
			defaultPassword = p.getProperty("password");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return defaultPassword;
	}
}

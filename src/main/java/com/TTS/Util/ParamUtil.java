package com.TTS.Util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.TTS.sercurity.CustomUserDetail;
import com.TTS.sercurity.JWT.JWTtoken;
import com.TTS.sercurity.JWT.TokenPovider;

@Component
public class ParamUtil {
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private ServletContext context;
	@Autowired
	private TokenPovider jwtPovider;

	/**
	 * Đọc chuỗi giá trị của tham số
	 * 
	 * @param name         tên tham số
	 * @param defaultValue giá trị mặc định
	 * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
	 */
	public String getString(String name, String defaultValue) {

		String value = request.getParameter(name);
		return (value != null ? value : defaultValue);
	}

	/**
	 * Đọc số nguyên giá trị của tham số
	 * 
	 * @param name         tên tham số
	 * @param defaultValue giá trị mặc định
	 * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tạ
	 */
	public int getInt(String name, int defaultValue) {

		try {
			int value = Integer.parseInt(request.getParameter(name));
			return value;
		} catch (Exception e) {
			// TODO: handle exception
			return defaultValue;
		}

	}

	/**
	 * Đọc số thực giá trị của tham số
	 * 
	 * @param name         tên tham số
	 * @param defaultValue giá trị mặc định
	 * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
	 */
	public double getDouble(String name, double defaultValue) {

		try {
			double value = Double.parseDouble(request.getParameter(name));
			return value;
		} catch (Exception e) {
			return defaultValue;
		}

	}

	public boolean getBoolean(String name, boolean defaultValue) {
		try {
			Boolean value = Boolean.parseBoolean(request.getParameter(name));
			System.out.println(value);
			return value;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("lỗi: " + e.getMessage());
			return defaultValue;
		}

	}

	/**
	 * Đọc giá trị thời gian của tham số
	 * 
	 * @param name    tên tham số
	 * @param pattern là định dạng thời gian
	 * @return giá trị tham số hoặc null nếu không tồn tại
	 * @throws RuntimeException lỗi sai định dạng
	 */
	public Date StringToDate(String datetring, String pattern) {
		try {
			Date date1 = new SimpleDateFormat(pattern).parse(datetring);
//			System.err.println(date1);
			return date1;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	public String dateToString(Date date, String panter) {
		try {
			SimpleDateFormat formater = new SimpleDateFormat(panter);
			String dateString = formater.format(date);
			return dateString;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}

	public File Save(MultipartFile file, String path) throws IllegalStateException, IOException {
		if (!file.isEmpty()) {
			System.out.println(file.getOriginalFilename());
			String fileName = file.getOriginalFilename();
			File filea = new File(context.getRealPath("/docs/" + fileName));
			System.out.println(context.getRealPath("/docs/" + fileName));
			file.transferTo(filea);
			return filea;
		}
		return null;
	}

	public String getTokenFormRequest() {
		String token = request.getHeader("Authorization");
		if (StringUtils.hasText(token) && token.startsWith("bruh")) {
			return token.substring(5);
		}

		return null;
	}

	public Integer getAccountId() {
		String token = this.getTokenFormRequest();
		CustomUserDetail auth = (CustomUserDetail) jwtPovider.getUserFormToken(token).getPrincipal();
		return auth.getIdAccount();
	}
}

package com.TTS.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
@Service
public class SessionService {
	@Autowired
	HttpSession session;

	public <T> T get(String name) {
		T value = (T) session.getAttribute(name);

		return value;
	}

	/**
	 * Xóa attribute trong session
	 * 
	 * @param name tên attribute cần xóa
	 */
	public void set(String name, Object value) {
		session.setAttribute(name, value);
	}

	/**
	 * Xóa attribute trong session
	 * 
	 * @param name tên attribute cần xóa
	 */

	public void remove(String name) {
		session.invalidate();
//		session.removeAttribute(name);
	}
}

package com.TTS.Util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@Slf4j
public class CookieService {
    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;

    /**
     * Đọc cookie từ request
     *
     * @param name tên cookie cần đọc
     * @return đối tượng cookie đọc được hoặc null nếu không tồn tại
     */
    public Cookie getCookie(String name) {
        /*
         * đọc giá trị của cookie cần đcọ
         *
         * @param nam tên cookie cần đcọ
         *
         * @return chuỗi giá trị đcọ được hoặc rỗng nếu không tồn tại
         */
        Cookie cookie;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            System.out.println("tìm ra danh sách cookie với tên");
            for (Cookie cookie2 : cookies) {

                if (cookie2.getName().equals(name)) {
                    return cookie2;
                }
            }
        }
        return null;

    }

    /*
     * tạo và gửi cookie về client
     *
     * @param name tên cookie
     *
     * @param value giá trị của cookie
     *
     * @param hours thời gian tồn tại (giờ) return đối tược cookie đã tạo
     */
    public String getvalue(String name) {
        Cookie cookies[] = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
//			System.out.println(	cookie.getValue());
                if (cookie.getName().equals(name)) {
//                    log.info("vừa tìm thấy cookei");
                    return cookie.getValue();
                }
            }
        }

        return null;
    }

    /**
     * Tạo và gửi cookie về client
     *
     * @param name  tên cookie
     * @param value giá trị cookie
     * @param hours thời hạn (giờ)
     * @return đối tượng cookie đã tạo
     */
    public Cookie add(String name, String value, int hours, boolean httpOnly) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(hours);
        cookie.setPath("/");
        cookie.setHttpOnly(httpOnly);
        response.addCookie(cookie);
        return cookie;

    }

    public Cookie remoce(String name) {
        Cookie cookie = new Cookie(name, "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return cookie;
    }
}

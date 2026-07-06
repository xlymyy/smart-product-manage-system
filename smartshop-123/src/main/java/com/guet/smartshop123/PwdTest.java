package com.guet.smartshop123;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PwdTest {
    public static void main(String[] args) {
        // 这里写你数据库里用户的明文密码
        String rawPassword = "123456";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encryptPwd = encoder.encode(rawPassword);
        System.out.println("加密后的密码：" + encryptPwd);
    }
}
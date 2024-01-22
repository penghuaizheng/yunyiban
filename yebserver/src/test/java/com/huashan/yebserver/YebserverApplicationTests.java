package com.huashan.yebserver;

import com.huashan.yebserver.domain.Admin;
import com.huashan.yebserver.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class YebserverApplicationTests {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {
        Admin admin = new Admin();
        admin.setId(1);
        admin.setUsername("admin");
//        String s = jwtUtil.generateToken(admin);
//        System.out.println(s);
        String encode = passwordEncoder.encode("123456");
        System.out.println(encode);
    }

}

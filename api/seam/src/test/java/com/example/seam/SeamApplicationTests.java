package com.example.seam;

import com.example.seam.mapper.UsersMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SeamApplicationTests {

    @Autowired
    UsersMapper usersMapper;

    @Test
    void contextLoads() {
        int modify = usersMapper.modify(1, "22", "22", "22", "22", "22", "22");
        System.out.println(modify);
    }

}

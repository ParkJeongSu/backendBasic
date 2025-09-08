package kr.co.aim.api.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles({"scheduler"})
class DataTransferServiceTest {

    @Autowired
    private DataTransferService dataTransferService;

    @Test
    void test1(){
        //dataTransferService.transferUsersToDb2();
    }

}
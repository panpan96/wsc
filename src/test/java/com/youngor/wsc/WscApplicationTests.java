package com.youngor.wsc;

import com.youngor.wsc.service.ItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
public class WscApplicationTests {

    @Autowired
    private ItemService itemService;

    @Test
    public void contextLoads() {


        itemService.saveItemMessage();

    }
}




package com.icongtai.test;

import com.icongtai.geo.GeoApiApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;


/**
 * Unit test for simple App.
 */
// 加载测试需要的类，一定要加入 Spring Boot 的启动类，其次需要加入本类。
@SpringBootTest(classes = {GeoApiApplication.class, AppTest.class})
@Component
public class AppTest {
    /**
     * Rigorous Test :-)
     */


}

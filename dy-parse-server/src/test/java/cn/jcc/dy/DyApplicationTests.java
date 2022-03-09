package cn.jcc.dy;

import cn.jcc.dy.pojo.po.VideoInfo;
import cn.jcc.dy.sdk.DyClient;
import cn.jcc.dy.service.impl.DyClientServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
class DyApplicationTests {
    @Autowired
    DyClient dyClient;
    @Autowired
    private DyClientServiceImpl dyClientService;


    @Test
    void contextLoads() {

       }

}

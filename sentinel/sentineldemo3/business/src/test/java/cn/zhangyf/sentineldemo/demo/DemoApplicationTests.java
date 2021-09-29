package cn.zhangyf.sentineldemo.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BusinessApplication.class)
public class DemoApplicationTests {
    @Autowired
    WebApplicationContext webApplicationContext;
    MockMvc mockMvc;

    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void test1() throws Exception {
        int cnt = 0;
        while (cnt++ < 1000) {
            System.out.println("########################################### " + cnt);
            try {
                String uri = "/hello";
                mockMvc.perform(MockMvcRequestBuilders.get(uri)
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
//                    .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                        .andDo(MockMvcResultHandlers.print());
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

        // 查看 限流日志
        // cat /Users/zhangyunfei/logs/csp/com-intellij-rt-execution-junit-JUnitStarter-metrics.log.2021-09-20
    }


}

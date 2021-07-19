package cn.zyfvir.service1.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class Service2Client {

    @Autowired
    private RestTemplate restTemplate;

    public void changeMoney(int id, int count) {
        String url = "http://127.0.0.1:8082/count/change?id=" + id + "&count=" + count;
        try {
            restTemplate.getForEntity(url, Void.class);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("create url {} ,error:", url);
            throw new RuntimeException(e.getMessage());
        }
    }

}

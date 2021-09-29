package cn.zhangyf.sentineldemo.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 *
 */
@RestController
public class DemoController {

    @Autowired
    private TestService service;

    @GetMapping(value = "/hello/{name}")
    public String apiHello(@PathVariable String name) {
        try {
            int t = new Random().nextInt(8000);
            Thread.sleep(t);
            System.out.println("t=" + t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("hello");
        return service.sayHello(name);
    }


    @GetMapping(value = "/hi")
    public String apiHi() {
        System.out.println("hi");
        return "hi";
    }
}

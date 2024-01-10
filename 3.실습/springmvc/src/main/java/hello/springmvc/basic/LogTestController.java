package hello.springmvc.basic;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class LogTestController {

    @ResponseBody
    @RequestMapping("/log-test")
    public String LogTest(){

        log.info("hihi");
        return "ok";
    }
}

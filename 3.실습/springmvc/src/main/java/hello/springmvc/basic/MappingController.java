package hello.springmvc.basic;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class MappingController {

    @RequestMapping("/hello-basic")
    public String helloBasic(){
        log.info("hello basic");
        return "ok";
    }

    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String getMappingV1(){
        log.info("get mapping v1");
        return "ok";
    }

    @GetMapping(value = "/mapping-get-v2")
    public String getMappingV2(){
        log.info("get mapping v2");
        return "ok";
    }

    @GetMapping("/mapping/{userId}")
    public String pathVariable (@PathVariable("userId") String userId){
        log.info("get mapping v3 {}", userId);
        return "ok";
    }

    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String multiPathVariable (@PathVariable("userId") String userId, @PathVariable("orderId")String orderId){
        log.info("get mapping v3 {} and {}", userId, orderId);
        return "ok";
    }
}

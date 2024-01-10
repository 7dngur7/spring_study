package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class ResponseBodyController {

    @RequestMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("hihi");
    }

    @RequestMapping("/response-body-string-v2")
    public ResponseEntity responseBodyV2() {
        return new ResponseEntity<String>("hihi",HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping("/response-body-string-v3")
    public String responseBodyV3() {
        return "hihi";
    }

    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseJsonV1(){
        HelloData helloData = new HelloData();
        helloData.setUsername("woohyuk");
        helloData.setAge(10);


        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }

}

package hello.springmvc.basic.request;


import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @RequestMapping("/request-body-string-v1")
    public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String requestbody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("reqbody = {}",requestbody);

        response.getWriter().write("hihihi");
    }

    @RequestMapping("/request-body-string-v2")
    public void requestBodyStringV2(ServletInputStream inputStream, Writer writer) throws IOException {
        String requestbody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("reqbody = {}",requestbody);

       writer.write("hihihi");
    }

    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity){
        String requestbody = httpEntity.getBody();

        log.info("reqbody = {}",requestbody);

        return new HttpEntity<>("hihihihih");
    }

    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String mybodystring) {

        log.info("reqbody = {}",mybodystring);

        return "hihihihi";
    }
}

package hello.servlet.web.frontcontroller.v4;


import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "FrontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {
    Map<String, ControllerV4> controllerV4Map = new HashMap<>();

    public FrontControllerServletV4() {
        controllerV4Map.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerV4Map.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerV4Map.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        ControllerV4 controllerV4 = controllerV4Map.get(uri);
        if (controllerV4 == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return; }

        Map<String, String> paramMap = getParamMap(req);
        Map<String, Object> objectMap = new HashMap<>();
        String viewPath = controllerV4.process(paramMap, objectMap);

        MyView myView = getMyView(viewPath);


        myView.render(objectMap, req, resp);

    }

    private static MyView getMyView(String viewName) {
        String viewPath = "/WEB-INF/views/"+ viewName +".jsp";
        MyView myView = new MyView(viewPath);
        return myView;
    }

    private  Map<String, String> getParamMap(HttpServletRequest req) {
        Map<String, String> paramMap = new HashMap<>();
        req.getParameterNames().asIterator().forEachRemaining(
                paramName -> paramMap.put(paramName, req.getParameter(paramName))
        );
        return paramMap;
    }


}

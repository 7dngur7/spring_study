package hello.servlet.web.frontcontroller.v5;


import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.yaml.snakeyaml.error.MarkedYAMLException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "FrontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {
    Map<String, Object> controllerMap = new HashMap<>();
    List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        putControllers();
        putHandlerAdapters();
    }

    private void putControllers() {
        controllerMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        controllerMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }
    private void putHandlerAdapters() {
         handlerAdapters.add(new ControllerV3HandlerAdapter());
         handlerAdapters.add(new ControllerV4HandlerAdapter());

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object handler = getHandler(req);

        if (handler==null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyHandlerAdapter adapter = getMyHandlerAdapter(handler);
        ModelView modelView = adapter.handle(req, resp,handler);
        MyView myView = getMyView(modelView);
        myView.render(modelView.getModel(), req, resp);

    }

    private static MyView getMyView(ModelView modelView) {
        String viewName = modelView.getViewName();
        MyView myView = getMyView(viewName);
        return myView;
    }

    private MyHandlerAdapter getMyHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handler)) {
                return adapter;
            } }
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler=" + handler);
    }

    private Object getHandler(HttpServletRequest req) {
        String uri = req.getRequestURI();

        Object handler = controllerMap.get(uri);
        return handler;
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
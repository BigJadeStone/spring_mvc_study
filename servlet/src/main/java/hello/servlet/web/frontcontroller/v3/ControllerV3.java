package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {

    //V3는 servlet 종속성을 제거했음. -> 파라미터로 map만 전달 받음.
    ModelView process(Map<String, String> paramMap);

}

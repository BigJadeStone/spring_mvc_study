package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
/*spring boot 3.0 이상부터는 클래스 레벨에 @Controller 을 붙여야만 핸들러로 인식한다고 함.
* RequestMappingHandlerMapping에서 핸들러로 인식한다고 함*/
//@Component    //빈 등록
//@RequestMapping   //클래스 레벨에 이게 붙으면 핸들러로 인식
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {
        return new ModelAndView("new-form");
    }

}

package hello.servlet.domain.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jsp/members")
public class MemberController {

    @GetMapping("/new-form")
    public String newForm() {
        System.out.println("newForm controller is run");
        return "new-form";
    }

    @GetMapping("/test")
    public String test() {
        System.out.println("test controller is run");
        return "test";
    }
}

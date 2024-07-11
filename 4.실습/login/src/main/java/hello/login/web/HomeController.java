package hello.login.web;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import hello.login.web.argumentResolver.Login;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController implements  SessionConst {

    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;



    //@GetMapping("/")
    public String home() {

        return "home";
    }
    //@GetMapping("/")
    public String homeLogin(@CookieValue(name = "memberId", required = false) Long memberId, Model model) {

        if(memberId== null){
            return "home";
        }

        Member member = memberRepository.findById(memberId);
        if(member==null){
            return "home";
        }

        model.addAttribute("member", member);
        return "loginHome";
    }

    //@GetMapping("/")
    public String homeLoginV2(HttpServletRequest request, Model model) {


        Member sessionMember = (Member)sessionManager.getSession(request);
        if(sessionMember==null){
            return "home";
        }

        model.addAttribute("member", sessionMember);
        return "loginHome";
    }

    //@GetMapping("/")
    public String homeLoginV3(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);
        if(session==null){
            return "home";
        }

        Member sessionMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if(sessionMember ==null){
            return "home";
        }

        model.addAttribute("member", sessionMember);
        return "loginHome";
    }

    //@GetMapping("/")
    public String homeLoginV3_5(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member sessionMember, Model model) {

        if(sessionMember ==null){
            return "home";
        }

        model.addAttribute("member", sessionMember);
        return "loginHome";
    }

    //@GetMapping("/")
    public String homeLoginV4(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member sessionMember, Model model) {

        if(sessionMember ==null){
            return "home";
        }

        model.addAttribute("member", sessionMember);
        return "loginHome";
    }

    @GetMapping("/")
    public String homeLoginV5(@Login Member sessionMember, Model model) {

        if(sessionMember ==null){
            return "home";
        }

        model.addAttribute("member", sessionMember);
        return "loginHome";
    }
}
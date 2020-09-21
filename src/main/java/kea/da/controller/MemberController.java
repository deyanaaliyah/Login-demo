package kea.da.controller;

import kea.da.model.Member;
import kea.da.repository.IMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MemberController {

    @Autowired
    IMember memberRepository;

    /*public MemberController(IMember memberRepository){
        this.memberRepository = memberRepository;
    }*/

    @GetMapping("/")
    public String index(HttpSession session){
        if(session.getAttribute("isLoggedIn") != null) {
            return "/html/main";
        }

        return "index";
    }

    @PostMapping("/")
    public String login(@ModelAttribute Member member, Model model, HttpSession session){
        //check credentials is from the arraylist
        Member memberEmailToLogIn = memberRepository.read(member.getEmail());
        //Member memberPwdToLogIn = memberRepository.read(member.getPassword());

        if(memberEmailToLogIn != null){
            session.setAttribute("isLoggedIn", "yes");
            model.addAttribute("members",memberRepository.readAll());

            return "/html/main";
        }
        return "index";
    }

    @GetMapping("/logout")
    public String logOutSession(HttpSession session){
        session.removeAttribute("isLoggedIn");

        if(session.getAttribute("isLoggedIn") != null){
            return "/html/main";
        }

        return "index";
    }
}
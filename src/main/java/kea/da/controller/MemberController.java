package kea.da.controller;

import kea.da.model.Member;
import kea.da.repository.IMember;
import kea.da.repository.MemberRepository;
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
            return "html/secret";
        }

        return "index";
    }

    @PostMapping("/")
    public String login(@ModelAttribute Member member, Model model, HttpSession session){
        //check credentials is from the arraylist
        Member memberToLogIn = memberRepository.read(member.getEmail());

        if(memberToLogIn != null){
            session.setAttribute("isLoggedIn", "yes");
            model.addAttribute("members",memberRepository.readAll());

            return "/html/secret";
        }
        return "index";
    }

    @GetMapping("/logout")
    public String logOutSession(HttpSession session){
        session.removeAttribute("isLoggedIn");

        if(session.getAttribute("isLoggedIn") != null){
            return "secret";
        }

        return "index";
    }
}
package com.example.ssu_lost.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginPageController {
// kakao
    @Value("${spring.kakao.client_id}")
    private String kakaoClientId;

    @Value("${spring.kakao.redirect_uri}")
    private String kakaoRedirectUri;
//google
    @Value("${spring.google.client_id}")
    private String googleClientId;

    @Value("${spring.google.redirect_uri}")
    private String googleRedirectUri;


    @GetMapping("/page")
    public String loginPage(Model model) {
        String kakaoLocation = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id="+kakaoClientId+"&redirect_uri="+kakaoRedirectUri;

        String googleLocation = "https://accounts.google.com/o/oauth2/v2/auth?client_id="+googleClientId+"&redirect_uri="+googleRedirectUri+"&response_type=code&scope=email profile";

        model.addAttribute("kakaoLocation", kakaoLocation);
        model.addAttribute("googleLocation", googleLocation);

        return "login";
    }

}

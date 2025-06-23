package org.scoula.controller;

import lombok.extern.log4j.Log4j2;
import org.scoula.security.account.domain.CustomUser;
import org.scoula.security.account.domain.MemberVO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Log4j2
@RequestMapping("/security")
@Controller
public class SecurityController {

    @GetMapping("/all") // 모두 접근 가능
    public void doAll() {
        log.info("do all can access everybody");
    }

    @GetMapping("/member") // MEMBER 또는 ADMIN 권한 필요
    public void doMember() {
        log.info("logined member");
    }

    @GetMapping("/admin") // ADMIN 권한 필요
    public void doAdmin() {
        log.info("admin only");
    }

    @GetMapping("/login")
    public void doLogin() {
        log.info("로그인 페이지로 전환");
    }

    @GetMapping("/logout")
    public void doLogout() {
        log.info("로그아웃 페이지로 전환");
    }

    // Principal
    // 가장 단순한 방식 -> 로그인한 사용자의 username만 필요할 때 사용
    @GetMapping("/member/principal")
    public void printUserDetailByPrincipal(Principal principal) {
        log.info("username ======> {}", principal.getName());
    }

    // Authentication
    // 사용자 이름뿐 아니라, 권한, 인증상태, 자격 증명 등 접근 가능
    @GetMapping("/member/authentication")
    public void printUserDetailByAuthentication(Authentication authentication) {
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        log.info("username ======> {}", userDetails.getUsername());
    }

    // @AuthenticationPrincipal
    // SpringSecurity가 authentication.getPrincipal()에서 꺼낸 객체를 직접 주입
    // -> CustomUserDetails (구현체)에 접근 가능 (CustomUser)
    @GetMapping("/member/authentication-principal")
    public void printUserDetailByAuthenticationPrincipal(@AuthenticationPrincipal CustomUser customUser) {
        MemberVO memberVO = customUser.getMember();
        log.info("member ======> {}", memberVO);
    }
}

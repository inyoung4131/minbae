package com.minbae.owner.controller;

import com.minbae.owner.comm.OwnerApiResponse;
import com.minbae.owner.dto.OwnerKakaoDto;
import com.minbae.owner.service.OAuthService;
import com.minbae.owner.service.OwnerService;
import com.minbae.sso.comm.ApiResponse;
import com.minbae.sso.controller.AdminController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@RequiredArgsConstructor
@Controller
public class OwnerController {

    private final OAuthService oAuthService;
    private final OwnerService ownerService;
    private final AdminController adminController;

    // 로그인 폼 이동 - before
//    @GetMapping("/loginForm/after/owner")
//    public String loginOwnerForm(){return "owner/login_form";}
    // 로그인 폼 이동 - after
    @GetMapping("/loginForm/owner")
    public String newloginOwnerFOrm(){return "owner/login_form_new";}

    // 0514 kakao하면서 추가
    @GetMapping("/oauth/kakao/owner")
    public String goCreateOwner(@RequestParam("code") String code, Model model){
        System.out.println("카카오에서 받은 code>>"+code);
        String kakaoAccessToken = oAuthService.getKakaoAccessToken(code);
        System.out.println("controller access_token : " + kakaoAccessToken);

        HashMap<String, Object> userInfo = oAuthService.getUserInfo(kakaoAccessToken);
        String kakaoOwnerIdx = (String) userInfo.get("id"); //
        String kakaoOwnerNickName = (String) userInfo.get("nickname");
        String kakaoOwnerEmail = (String) userInfo.get("email");
        String ownerPwd = kakaoOwnerIdx; //
        System.out.println("id="+kakaoOwnerIdx+" nickname="+kakaoOwnerNickName+" email="+kakaoOwnerEmail);

        // 클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록...
//        if (userInfo.get("email") != null) {
//            session.setAttribute("userId", userInfo.get("email"));
//            session.setAttribute("access_Token", access_Token);
//        }

        // 생각 1.
        // 카카오계정이메일이 존재할 때 -> DB에 해당 메일이 저장되어있지 않으면 회원가입 -> 있으면 로그인 완료처리
        OwnerKakaoDto ownerKakaoDto = new OwnerKakaoDto();
        if(ownerService.findOwnerDto(kakaoOwnerEmail, ownerPwd) == null){
            System.out.println("카카오로 회원가입되어 있지 않음. 회원가입 실행");

            ownerKakaoDto.setOwnerEmail(kakaoOwnerEmail);
            ownerKakaoDto.setOwnerNickName(kakaoOwnerNickName);
            ownerKakaoDto.setOwnerPwd(ownerPwd);

            ownerService.joinOwnerByKakao(ownerKakaoDto);
        }

        model.addAttribute("kakaoOwnerEmail", kakaoOwnerEmail);
        // 로그인 완료 -> session이 아니라 sessionStorage에 저장
        // 민배 자체 토큰 발급을 위해 AdminController login 메소드 호출
        //ApiResponse ownerLogined = adminController.login("owner", kakaoOwnerEmail, ownerPwd);
        // accessToken도 저장하기 위해 OwnerApiResponse에 바꿔서 추가로 저장
        //OwnerApiResponse ownerLoginedResponse = new OwnerApiResponse(ownerLogined.getStatus(), ownerLogined.getData(), ownerLogined.getMemberData(), kakaoAccessToken);
        // 해당 데이터를 받아서 클라쪽에서 처리...를 어떻게 해야하지?
        // ModelAndView는 아닌거 같은데..자바스크립트로 받아야하는데..아! 이 부분을 떼어서 login_form에서 api요청을 보낼까?
        // 아래 방법은 어떨까..
        //redirectAttributes.addFlashAttribute("ownerLoginedResponse", ownerLoginedResponse);

        return "redirect:owner/login_form";
    }


}

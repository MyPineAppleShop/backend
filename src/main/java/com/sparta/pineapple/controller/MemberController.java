package com.sparta.pineapple.controller;

import com.sparta.pineapple.dto.response.ResponseDto;
import com.sparta.pineapple.dto.request.LoginRequestDto;
import com.sparta.pineapple.dto.request.MemberRequestDto;
import com.sparta.pineapple.dto.response.ResponseDto;
import com.sparta.pineapple.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class MemberController {

  private final MemberService memberService;

  @RequestMapping(value = "/member/signup", method = RequestMethod.POST)
  public ResponseDto<?> signup(@RequestBody @Valid MemberRequestDto requestDto) {
    return memberService.createMember(requestDto);
  }

  @RequestMapping(value = "/member/login", method = RequestMethod.POST)
  public ResponseDto<?> login(@RequestBody @Valid LoginRequestDto requestDto,
      HttpServletResponse response
  ) {
    return memberService.login(requestDto, response);
  }

//  @RequestMapping(value = "/member/reissue", method = RequestMethod.POST)
//  public ResponseDto<?> reissue(HttpServletRequest request, HttpServletResponse response) {
//    return memberService.reissue(request, response);
//  }

  @RequestMapping(value = "/member/logout", method = RequestMethod.POST)
  public ResponseDto<?> logout(HttpServletRequest request) {
    return memberService.logout(request);
  }
}

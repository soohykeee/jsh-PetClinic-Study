package kr.co.jshpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.jshpetclinicstudy.infra.model.ResponseFormat;
import kr.co.jshpetclinicstudy.infra.model.ResponseStatus;
import kr.co.jshpetclinicstudy.service.MemberService;
import kr.co.jshpetclinicstudy.service.model.request.MemberRequestDto;
import kr.co.jshpetclinicstudy.service.model.request.TokenDto;
import kr.co.jshpetclinicstudy.service.model.response.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MemberController {

    private final MemberService memberService;

    /**
     * Create Member API (회원가입)
     *
     * @param create
     * @return
     */
    @PostMapping("/register")
    public ResponseFormat<Void> createMember(@RequestBody @Valid MemberRequestDto.CREATE create) {
        memberService.createMember(create);

        return ResponseFormat.success(ResponseStatus.SUCCESS_CREATE);
    }

    /**
     * Login Member API (로그인)
     *
     * @param login
     * @return
     */
    @PostMapping("/login")
    public ResponseFormat<MemberResponseDto.READ> loginMember(@RequestBody @Valid MemberRequestDto.LOGIN login) {
        return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, memberService.loginMember(login));
    }

    /**
     * Get Member By Identity API
     * ADMIN, USER 접근 가능
     *
     * @param identity
     * @return
     */
    @GetMapping("/members/get")
    public ResponseFormat<MemberResponseDto.READ> getMember(@RequestParam String identity) {
        return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, memberService.readMember(identity));
    }

    /**
     * Update Member API
     * ADMIN, USER 접근 가능
     *
     * @param update
     * @return
     */
    @PutMapping("/members/update")
    public ResponseFormat<Void> updateMember(@RequestBody @Valid MemberRequestDto.UPDATE update) {
        memberService.updateMember(update);

        return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
    }

    /**
     * Read(Get) Member API
     * ADMIN 접근 가능
     *
     * @param condition
     * @return
     */
    @PostMapping("/admin/search")
    public ResponseFormat<List<MemberResponseDto.READ>> getMembersByCondition(@RequestBody @Valid MemberRequestDto.CONDITION condition) {
        return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, memberService.getMembersByCondition(condition));
    }

    /**
     * Refresh
     *
     * @param tokenDto
     * @return
     */
    @GetMapping("/refresh")
    public ResponseFormat<TokenDto> refresh(@RequestBody @Valid TokenDto tokenDto) {
        return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, memberService.refreshAccessToken(tokenDto));
    }

}

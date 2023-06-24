package kr.co.jshpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.jshpetclinicstudy.infra.model.ResponseFormat;
import kr.co.jshpetclinicstudy.infra.model.ResponseStatus;
import kr.co.jshpetclinicstudy.service.MemberService;
import kr.co.jshpetclinicstudy.service.model.request.MemberRequestDto;
import kr.co.jshpetclinicstudy.service.model.response.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MemberController {

    private final MemberService memberService;

    /**
     * Create Member
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
     * Login Member
     *
     * @param login
     * @return
     */
    @PostMapping("/login")
    public ResponseFormat<MemberResponseDto.READ> loginMember(@RequestBody @Valid MemberRequestDto.LOGIN login) {
        return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, memberService.loginMember(login));
    }

    /**
     * Get Member By Identity
     *
     * @param identity
     * @return
     */
    @GetMapping("/members/{member_identity}")
    public ResponseFormat<MemberResponseDto.READ> getMember(@PathVariable(name = "member_identity") String identity) {
        return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, memberService.readMember(identity));
    }

    /**
     * Update Member
     *
     * @param update
     * @return
     */
    @PutMapping("/update")
    public ResponseFormat<Void> updateMember(@RequestBody @Valid MemberRequestDto.UPDATE update) {
        memberService.updateMember(update);

        return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
    }
}

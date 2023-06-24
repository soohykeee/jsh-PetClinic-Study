package kr.co.jshpetclinicstudy.service;


import jakarta.transaction.Transactional;
import kr.co.jshpetclinicstudy.infra.exception.DuplicatedException;
import kr.co.jshpetclinicstudy.infra.exception.NotFoundException;
import kr.co.jshpetclinicstudy.infra.exception.WrongPasswordException;
import kr.co.jshpetclinicstudy.infra.jwt.JwtProvider;
import kr.co.jshpetclinicstudy.infra.model.ResponseStatus;
import kr.co.jshpetclinicstudy.persistence.entity.Member;
import kr.co.jshpetclinicstudy.persistence.entity.enums.Role;
import kr.co.jshpetclinicstudy.persistence.repository.MemberRepository;
import kr.co.jshpetclinicstudy.service.model.mapper.MemberMapper;
import kr.co.jshpetclinicstudy.service.model.request.MemberRequestDto;
import kr.co.jshpetclinicstudy.service.model.response.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final MemberMapper memberMapper;

    private final PasswordEncoder passwordEncoder;

    private final JwtProvider jwtProvider;

    @Transactional
    public void createMember(MemberRequestDto.CREATE create) {
//        final Member member = memberMapper.toEntity(create);

        Member member = Member.builder()
                .identity(create.getIdentity())
                .password(passwordEncoder.encode(create.getPassword()))
                .name(create.getName())
                .role(Role.valueOf(create.getRole()))
                .build();

        isIdentity(create.getIdentity());

        memberRepository.save(member);
    }


    public MemberResponseDto.READ loginMember(MemberRequestDto.LOGIN login) {
        final Optional<Member> member = memberRepository.findMemberByIdentity(login.getIdentity());

        isMember(member);

        isPassword(login.getPassword(), member.get().getPassword());

        return MemberResponseDto.READ.builder()
                .memberId(member.get().getId())
                .identity(member.get().getIdentity())
                .name(member.get().getName())
                .role(String.valueOf(member.get().getRole()))
                .token(jwtProvider.createToken(member.get().getIdentity(), String.valueOf(member.get().getRole())))
                .build();
//        return memberMapper.toReadDto(member.get());
    }

    @Transactional
    public void updateMember(MemberRequestDto.UPDATE update) {
        final Optional<Member> member = memberRepository.findById(update.getMemberId());

        isMember(member);

        member.get().updateMember(update);

        memberRepository.save(member.get());
    }


//    public List<MemberResponseDto.READ> getMembersByCondition(MemberRequestDto.CONDITION condition) {
//
//    }

    public MemberResponseDto.READ readMember(String identity) {
        final Optional<Member> member = memberRepository.findMemberByIdentity(identity);

        isMember(member);

        return memberMapper.toReadDto(member.get());
    }

    private void isMember(Optional<Member> member) {
        if (member.isEmpty()) {
            throw new NotFoundException(ResponseStatus.FAIL_MEMBER_NOT_FOUND);
        }
    }

    private void isIdentity(String identity) {
        if (memberRepository.existsByIdentity(identity)) {
            throw new DuplicatedException(ResponseStatus.FAIL_MEMBER_IDENTITY_DUPLICATED);
        }
    }

    private void isPassword(String requestPassword, String getPassword) {
        if (!passwordEncoder.matches(requestPassword, getPassword)) {
            throw new WrongPasswordException(ResponseStatus.FAIL_MEMBER_PASSWORD_NOT_MATCHED);
//            throw new BadCredentialsException("비밀번호를 확인해주세요.");
        }
    }
}
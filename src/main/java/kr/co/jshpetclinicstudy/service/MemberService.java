package kr.co.jshpetclinicstudy.service;


import jakarta.transaction.Transactional;
import kr.co.jshpetclinicstudy.infra.exception.DuplicatedException;
import kr.co.jshpetclinicstudy.infra.exception.InvalidRequestException;
import kr.co.jshpetclinicstudy.infra.exception.NotFoundException;
import kr.co.jshpetclinicstudy.infra.jwt.JwtProvider;
import kr.co.jshpetclinicstudy.infra.model.ResponseStatus;
import kr.co.jshpetclinicstudy.persistence.entity.Member;
import kr.co.jshpetclinicstudy.persistence.entity.Token;
import kr.co.jshpetclinicstudy.persistence.entity.enums.Role;
import kr.co.jshpetclinicstudy.persistence.repository.MemberRepository;
import kr.co.jshpetclinicstudy.persistence.repository.TokenRepository;
import kr.co.jshpetclinicstudy.persistence.repository.search.MemberSearchRepository;
import kr.co.jshpetclinicstudy.service.model.mapper.MemberMapper;
import kr.co.jshpetclinicstudy.service.model.request.MemberRequestDto;
import kr.co.jshpetclinicstudy.service.model.request.TokenDto;
import kr.co.jshpetclinicstudy.service.model.response.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final TokenRepository tokenRepository;

    private final MemberSearchRepository memberSearchRepository;

    private final MemberMapper memberMapper;

    private final PasswordEncoder passwordEncoder;

    private final JwtProvider jwtProvider;

    @Transactional
    public void createMember(MemberRequestDto.CREATE create) {

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
//                .token(jwtProvider.createToken(member.get().getIdentity(), String.valueOf(member.get().getRole())))
                .token(TokenDto.builder()
                        .accessToken(jwtProvider.createToken(member.get().getIdentity(), String.valueOf(member.get().getRole())))
//                        .refreshToken(member.get().getRefreshToken())
                        .refreshToken(createRefreshToken(member.get()))
                        .build())
                .build();
    }

    @Transactional
    public void updateMember(MemberRequestDto.UPDATE update) {
        final Optional<Member> member = memberRepository.findById(update.getMemberId());

        isMember(member);

        member.get().updateMember(update);

        memberRepository.save(member.get());
    }

    @Transactional
    public MemberResponseDto.READ readMember(String identity) {
        final Optional<Member> member = memberRepository.findMemberByIdentity(identity);

        isMember(member);

        return MemberResponseDto.READ.builder()
                .memberId(member.get().getId())
                .identity(member.get().getIdentity())
                .name(member.get().getName())
                .role(String.valueOf(member.get().getRole()))
//                .token(jwtProvider.createToken(member.get().getIdentity(), member.get().getRole().getUserRole()))
                .build();
    }

    public List<MemberResponseDto.READ> getMembersByCondition(MemberRequestDto.CONDITION condition) {
        final List<Member> members = memberSearchRepository.find(condition);

        return members.stream()
                .map(memberMapper::toReadDto)
                .collect(Collectors.toList());
    }

    /*
    ======== Refresh Token ========
     */

    /**
     * RefreshToken 생성
     * Redis 내부에는, refreshToken:memberId : tokenValue 형태로 저장
     *
     * @param member
     * @return
     */
    public String createRefreshToken(Member member) {
        Token token = tokenRepository.save(
                Token.builder()
                        .id(member.getId())
                        .refreshToken(UUID.randomUUID().toString())
                        .expiration(120)
                        .build()
        );

        return token.getRefreshToken();
    }

    public Token validRefreshToken(Member member, String refreshToken) {
        Optional<Token> token = tokenRepository.findById(member.getId());

        isToken(token);

        // redis에 해당 유저의 토큰이 존재하는지 체크
        if (token.get().getRefreshToken() == null) {
            return null;
        } else {
            // refreshToken은 있지만, 만료시간이 얼마 남지 않았다면 만료시간 연장
            if (token.get().getExpiration() < 10) {
                token.get().setExpiration(1000);
                tokenRepository.save(token.get());
            }

            // token이 같은지 비교
            if (!token.get().getRefreshToken().equals(refreshToken)) {
                return null;
            } else {
                return token.get();
            }
        }
    }

    public TokenDto refreshAccessToken(TokenDto tokenDto) {
        String identity = jwtProvider.getIdentity(tokenDto.getAccessToken());

        Optional<Member> member = memberRepository.findMemberByIdentity(identity);

        isMember(member);

        Token refreshToken = validRefreshToken(member.get(), tokenDto.getRefreshToken());

        isRefreshToken(refreshToken);

        return TokenDto.builder()
                .accessToken(jwtProvider.createToken(identity, String.valueOf(member.get().getRole())))
                .refreshToken(refreshToken.getRefreshToken())
                .build();

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
            throw new InvalidRequestException(ResponseStatus.FAIL_MEMBER_PASSWORD_NOT_MATCHED);
        }
    }

    private void isToken(Optional<Token> token) {
        if (token.isEmpty()) {
            throw new NotFoundException(ResponseStatus.FAIL_TOKEN_NOT_FOUND);
        }
    }

    private void isRefreshToken(Token refreshToken) {
        if (refreshToken == null) {
            throw new InvalidRequestException(ResponseStatus.FAIL_LOGIN_NOT_SUCCESS);
        }
    }
}

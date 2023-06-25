package kr.co.jshpetclinicstudy.service;

import kr.co.jshpetclinicstudy.persistence.repository.MemberRepository;
import kr.co.jshpetclinicstudy.persistence.repository.OwnerRepository;
import kr.co.jshpetclinicstudy.service.model.response.MemberResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void readMember() {

        MemberResponseDto.READ read = memberService.readMember("user1");

        System.out.println(read.getRole());
        System.out.println(read.getToken());

    }
}
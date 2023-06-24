package kr.co.jshpetclinicstudy.service.model.mapper;

import kr.co.jshpetclinicstudy.infra.jwt.JwtProvider;
import kr.co.jshpetclinicstudy.persistence.entity.Member;
import kr.co.jshpetclinicstudy.service.model.request.MemberRequestDto;
import kr.co.jshpetclinicstudy.service.model.response.MemberResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    Member toEntity(MemberRequestDto.CREATE create);

    @Mapping(target = "memberId", source = "id")
    MemberResponseDto.READ toReadDto(Member member);

}

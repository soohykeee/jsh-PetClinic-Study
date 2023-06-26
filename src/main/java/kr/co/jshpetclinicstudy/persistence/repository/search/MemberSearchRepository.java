package kr.co.jshpetclinicstudy.persistence.repository.search;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.jshpetclinicstudy.persistence.entity.Member;
import kr.co.jshpetclinicstudy.persistence.entity.QMember;
import kr.co.jshpetclinicstudy.persistence.entity.enums.Role;
import kr.co.jshpetclinicstudy.service.model.request.MemberRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberSearchRepository {

    private final JPAQueryFactory queryFactory;

    private final QMember member = QMember.member;

    public List<Member> find(MemberRequestDto.CONDITION condition) {
        return queryFactory
                .selectFrom(member)
                .where(
                        memberIdIn(condition.getMemberIds()),
                        memberNameEq(condition.getName()),
                        memberRoleEq(condition.getRole())
                )
                .fetch();
    }

    private BooleanExpression memberIdIn(List<Long> memberIds) {
        if (CollectionUtils.isEmpty(memberIds)) {
            return null;
        }

        return member.id.in(memberIds);
    }

    private BooleanExpression memberNameEq(String name) {
        if (!StringUtils.hasText(name)) {
            return null;
        }

        return member.name.eq(name);
    }

    private BooleanExpression memberRoleEq(String role) {
        if (!StringUtils.hasText(role)) {
            return null;
        }

        return member.role.eq(Role.valueOf(role));
    }
}

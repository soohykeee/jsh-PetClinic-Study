package kr.co.jshpetclinicstudy.persistence.repository.search;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.jshpetclinicstudy.persistence.entity.Owner;
import kr.co.jshpetclinicstudy.persistence.entity.QOwner;
import kr.co.jshpetclinicstudy.service.model.request.OwnerRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OwnerSearchRepository {

    private final JPAQueryFactory queryFactory;

    private final QOwner owner = QOwner.owner;

    public List<Owner> find(OwnerRequestDto.CONDITION condition) {
        return queryFactory
                .selectFrom(owner)
                .where(
                        ownerIdIn(condition.getOwnerIds()),
                        ownerFirstNameEq(condition.getFirstName()),
                        ownerCityContain(condition.getCity()),
                        ownerTelephoneEq(condition.getTelephone())
                )
                .fetch();
    }

    private BooleanExpression ownerIdIn(List<Long> ownerIds) {
        if (CollectionUtils.isEmpty(ownerIds)) {
            return null;
        }

        return owner.id.in(ownerIds);
    }

    private BooleanExpression ownerFirstNameEq(String firstName) {
        if (!StringUtils.hasText(firstName)) {
            return null;
        }

        return owner.firstName.eq(firstName);
    }

    private BooleanExpression ownerCityContain(String city) {
        if (!StringUtils.hasText(city)) {
            return null;
        }

        return owner.city.contains(city);
    }

    private BooleanExpression ownerTelephoneEq(String telephone) {
        if (!StringUtils.hasText(telephone)) {
            return null;
        }

        return owner.telephone.eq(telephone);
    }

}

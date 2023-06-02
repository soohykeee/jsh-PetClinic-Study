package kr.co.jshpetclinicstudy.persistence.repository.search;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.jshpetclinicstudy.persistence.entity.QPet;
import kr.co.jshpetclinicstudy.persistence.entity.QVet;
import kr.co.jshpetclinicstudy.persistence.entity.QVisit;
import kr.co.jshpetclinicstudy.persistence.entity.Visit;
import kr.co.jshpetclinicstudy.service.model.request.VisitRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class VisitSearchRepository {

    private final JPAQueryFactory queryFactory;

    private final QVisit visit = QVisit.visit;

    private final QPet pet = QPet.pet;

    private final QVet vet = QVet.vet;

    public List<Visit> find(VisitRequestDto.CONDITION condition) {
        return queryFactory
                .selectDistinct(visit)
                .from(visit)
                .join(pet, visit.pet).fetchJoin()
                .join(vet, visit.vet).fetchJoin()
                .where(
                        visitIdIn(condition.getVisitIds()),
                        visitDateBetween(condition.getStartDate(), condition.getEndDate()),
                        visitOwnerFirstNameEq(condition.getOwnerFirstName()),
                        visitPetNameEq(condition.getPetName()),
                        visitVetFirstNameEq(condition.getVetFirstName())
                )
                .fetch();
    }

    private BooleanExpression visitIdIn(List<Long> visitIds) {
        if (CollectionUtils.isEmpty(visitIds)) {
            return null;
        }

        return visit.id.in(visitIds);
    }

    private BooleanExpression visitDateBetween(LocalDate startDate, LocalDate endDate) {
        if (startDate == null && endDate == null) {
            return null;
        }

        return visit.visitDate.between(startDate, endDate);
    }

    private BooleanExpression visitOwnerFirstNameEq(String ownerFirstName) {
        if (!StringUtils.hasText(ownerFirstName)) {
            return null;
        }

        return visit.pet.owner.firstName.eq(ownerFirstName);
    }

    private BooleanExpression visitPetNameEq(String petName) {
        if (!StringUtils.hasText(petName)) {
            return null;
        }

        return visit.pet.name.eq(petName);
    }

    private BooleanExpression visitVetFirstNameEq(String vetFirstName) {
        if (!StringUtils.hasText(vetFirstName)) {
            return null;
        }

        return visit.vet.firstName.eq(vetFirstName);
    }

}

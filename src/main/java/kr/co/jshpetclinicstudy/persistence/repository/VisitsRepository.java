package kr.co.jshpetclinicstudy.persistence.repository;

import kr.co.jshpetclinicstudy.persistence.entity.Visits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitsRepository extends JpaRepository<Visits, Long> {

    @Query(value = "select v.visit_id, v.visit_date, v.description, v.pet_id " +
            "from tbl_visits v " +
            "where v.pet_id=:petId", nativeQuery = true)
    List<Visits> findVisitListByPetId(Long petId);

}

package kr.co.jshpetclinicstudy.persistence.repository;

import kr.co.jshpetclinicstudy.persistence.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {

 /*   @Query(value = "select v.visit_id, v.visit_date, v.description, v.pet_id " +
            "from tbl_visits v " +
            "where v.pet_id=:petId", nativeQuery = true)
    List<Visit> findVisitListByPetId(Long petId);
*/

    @Query("select v " +
            "from Visit v " +
            "where v.pet.id=:petId")
    List<Visit> findVisitsByPetId(Long petId);

    @Query("select v " +
            "from Visit v " +
            "where v.pet.owner.id=:ownerId")
    List<Visit> findVisitsByOwnerId(Long ownerId);

}

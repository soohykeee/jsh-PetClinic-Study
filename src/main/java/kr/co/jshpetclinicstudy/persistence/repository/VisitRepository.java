package kr.co.jshpetclinicstudy.persistence.repository;

import kr.co.jshpetclinicstudy.persistence.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {

    @Query("select v " +
            "from Visit v " +
            "join fetch v.pet " +
            "join fetch v.vet " +
            "where v.pet.id=:petId")
    List<Visit> findVisitsByPetId(Long petId);

    @Query("select v " +
            "from Visit v " +
            "join fetch v.pet.owner " +
            "where v.pet.owner.id=:ownerId")
    List<Visit> findVisitsByOwnerId(Long ownerId);

    @Query("select v " +
            "from Visit v " +
            "join fetch v.vet " +
            "join fetch v.pet " +
            "join fetch v.pet.owner " +
            "where v.vet.id=:vetId")
    List<Visit> findVisitsByVetId(Long vetId);

}

package kr.co.jshpetclinicstudy.persistence.repository;

import kr.co.jshpetclinicstudy.persistence.entity.Pets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetsRepository extends JpaRepository<Pets, Long> {

    @Query(value = "select p.pet_id, p.name, p.birth_date, p.pet_type, p.owner_id " +
            "from tbl_pets p " +
            "where p.owner_id=:ownerId", nativeQuery = true)
    List<Pets> findPetListByOwnerId(Long ownerId);
}

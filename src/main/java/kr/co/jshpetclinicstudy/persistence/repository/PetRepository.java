package kr.co.jshpetclinicstudy.persistence.repository;

import kr.co.jshpetclinicstudy.persistence.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    @Query(value = "select p.pet_id, p.name, p.birth_date, p.pet_type, p.owner_id " +
            "from tbl_pets p " +
            "where p.owner_id=:ownerId", nativeQuery = true)
    List<Pet> findPetListByOwnerId(Long ownerId);
}

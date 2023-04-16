package kr.co.jshpetclinicstudy.persistence.repository;

import kr.co.jshpetclinicstudy.persistence.entity.Owner;
import kr.co.jshpetclinicstudy.persistence.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    /*@Query(value = "select p.pet_id, p.name, p.birth_date, p.pet_type, p.owner_id " +
            "from tbl_pets p " +
            "where p.owner_id=:ownerId", nativeQuery = true)
    List<Pet> findPetListByOwnerId(Long ownerId);
*/

    @Query("select p " +
            "from Pet p " +
            "where p.owner.id=:ownerId")
    List<Pet> findPetsByOwnerId(Long ownerId);

    List<Pet> findPetsByOwner(Owner owner);

    // 테스트 코드 사용 위한 메서드
    @Query("select p " +
            "from Pet p " +
            "where p.name=:name")
    Optional<Pet> findPetByName(String name);

}

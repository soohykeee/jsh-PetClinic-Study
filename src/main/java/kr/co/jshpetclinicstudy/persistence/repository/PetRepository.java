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

    @Query("select p " +
            "from Pet p " +
            "join fetch p.owner " +
            "where p.owner.id=:ownerId")
    List<Pet> findPetsByOwnerId(Long ownerId);

    // 테스트 코드 사용 위한 메서드
    Optional<Pet> findPetByName(String name);

}

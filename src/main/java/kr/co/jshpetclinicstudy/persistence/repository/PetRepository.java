package kr.co.jshpetclinicstudy.persistence.repository;


import kr.co.jshpetclinicstudy.persistence.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    // 테스트 코드 사용 위한 메서드
    Optional<Pet> findPetByName(String name);

}

package kr.co.jshpetclinicstudy.persistence.repository;

import kr.co.jshpetclinicstudy.persistence.entity.Owners;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnersRepository extends JpaRepository<Owners, Long> {

    @Query("select Owners.id " +
            "from Owners " +
            "where Owners.telephone=: teleephone")
    Optional<Owners> findOwnerByTelephone(String telephone);

}

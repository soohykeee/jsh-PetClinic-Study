package kr.co.jshpetclinicstudy.persistence.repository;

import kr.co.jshpetclinicstudy.persistence.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    @Query("select o " +
            "from Owner o " +
            "where o.telephone=:telephone ")
    Optional<Owner> findOwnerByTelephone(String telephone);

    boolean existsByTelephone(String telephone);
}

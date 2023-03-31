package kr.co.jshpetclinicstudy.persistence.repository;

import kr.co.jshpetclinicstudy.persistence.entity.Owners;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnersRepository extends JpaRepository<Owners, Long> {

    @Query(value = "select o.owner_id, o.address, o.city, o.first_name, o.last_name, o.telephone " +
            "from tbl_owners o " +
            "where o.telephone=:telephone", nativeQuery = true)
    Optional<Owners> findOwnerByTelephone(String telephone);

}

package kr.co.jshpetclinicstudy.persistence.repository;

import kr.co.jshpetclinicstudy.persistence.entity.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
}

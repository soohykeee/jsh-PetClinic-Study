package kr.co.jshpetclinicstudy.persistence.repository;

import kr.co.jshpetclinicstudy.persistence.entity.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {

    List<Specialty> findAllBySpecialtyNameIn(List<String> name);

}

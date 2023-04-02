package kr.co.jshpetclinicstudy.service;

import kr.co.jshpetclinicstudy.persistence.entity.Specialties;
import kr.co.jshpetclinicstudy.persistence.entity.Vets;
import kr.co.jshpetclinicstudy.persistence.entity.VetsSpecialties;
import kr.co.jshpetclinicstudy.persistence.repository.SpecialtiesRepository;
import kr.co.jshpetclinicstudy.persistence.repository.VetsRepository;
import kr.co.jshpetclinicstudy.persistence.repository.VetsSpecialtiesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VetsSpecialtiesService {

    private final VetsSpecialtiesRepository vetsSpecialtiesRepository;
    private final VetsRepository vetsRepository;
    private final SpecialtiesRepository specialtiesRepository;

    public void create(Specialties specialties,
                       Vets vets){
        final VetsSpecialties vetsSpecialties = VetsSpecialties.builder()
                .specialties(specialties)
                .vets(vets)
                .build();
        vetsSpecialtiesRepository.save(vetsSpecialties);
    }

    public List<Vets> getVet(){
        List<Vets> list = vetsRepository.findAll();
        return list;
    }

    //~

    public void delete(){
        /** 학생이랑 수강  과목이면
         *
         * 1. 학위 테이블 -> Vet(Join) -> null 로 만듬
         * 2. 수의사 테이블 -> Spec(Join) -> null로 만듬
*               * spec.setVet(){
         *          *   this.vet = null;
         *          * }
         *          *
         *          * SpecRepo.save(spec);

         * 3. 연결되어 있지 않은 테이블 찾아 spec.getVet() == null 이면 -> 연관이 되어 있지 않음을 뜻함 -> 즉, SpecRepo.delete(spec)
         * 4. 반대 Vets도 3번과 동일
         *
         *

         */
    }
}

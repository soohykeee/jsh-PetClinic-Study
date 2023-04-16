package kr.co.jshpetclinicstudy.dummy;

import kr.co.jshpetclinicstudy.persistence.entity.Owner;
import kr.co.jshpetclinicstudy.persistence.entity.Pet;
import kr.co.jshpetclinicstudy.persistence.entity.Type;
import kr.co.jshpetclinicstudy.persistence.entity.Vet;
import kr.co.jshpetclinicstudy.persistence.repository.OwnerRepository;
import kr.co.jshpetclinicstudy.persistence.repository.PetRepository;
import kr.co.jshpetclinicstudy.persistence.repository.VetRepository;
import kr.co.jshpetclinicstudy.service.OwnerService;
import kr.co.jshpetclinicstudy.service.PetService;
import kr.co.jshpetclinicstudy.service.VetService;
import kr.co.jshpetclinicstudy.service.VisitService;
import kr.co.jshpetclinicstudy.service.model.request.OwnerRequestDto;
import kr.co.jshpetclinicstudy.service.model.request.PetRequestDto;
import kr.co.jshpetclinicstudy.service.model.request.VetRequestDto;
import kr.co.jshpetclinicstudy.service.model.request.VisitRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 데이터 쌓기 위한 테스트 코드들
 */
@SpringBootTest
public class DummyDataForTest {

    @Autowired
    private OwnerService ownerService;
    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private PetService petService;
    @Autowired
    private PetRepository petRepository;

    @Autowired
    private VisitService visitService;

    @Autowired
    private VetService vetService;
    @Autowired
    private VetRepository vetRepository;


    @Test
    void createOwner() {
        OwnerRequestDto.CREATE create1 = OwnerRequestDto.CREATE.builder()
                .firstName("수혁")
                .lastName("장")
                .address("도봉구 창동")
                .city("서울시")
                .telephone("01064564655")
                .build();

        OwnerRequestDto.CREATE create2 = OwnerRequestDto.CREATE.builder()
                .firstName("영희")
                .lastName("김")
                .address("성북구 정릉동")
                .city("서울시")
                .telephone("01011112222")
                .build();

        OwnerRequestDto.CREATE create3 = OwnerRequestDto.CREATE.builder()
                .firstName("철수")
                .lastName("이")
                .address("은평구 응암동")
                .city("서울시")
                .telephone("01012341234")
                .build();

        OwnerRequestDto.CREATE create4 = OwnerRequestDto.CREATE.builder()
                .firstName("지훈")
                .lastName("최")
                .address("용산구 한남동")
                .city("서울시")
                .telephone("01099998888")
                .build();

        ownerService.createOwner(create1);
        ownerService.createOwner(create2);
        ownerService.createOwner(create3);
        ownerService.createOwner(create4);
    }

    @Test
    void createPet() {
        Optional<Owner> owner1 = ownerRepository.findOwnerByTelephone("01064564655");
        Optional<Owner> owner2 = ownerRepository.findOwnerByTelephone("01011112222");
        Optional<Owner> owner3 = ownerRepository.findOwnerByTelephone("01012341234");
        Optional<Owner> owner4 = ownerRepository.findOwnerByTelephone("01099998888");

        // 주인 1
        PetRequestDto.CREATE create1 = PetRequestDto.CREATE.builder()
                .name("멍멍이푸들")
                .birthDate(LocalDate.of(2021, 1, 9))
                .owner(owner1.get())
                .type(String.valueOf(Type.푸들))
                .build();

        // 주인 2
        PetRequestDto.CREATE create2 = PetRequestDto.CREATE.builder()
                .name("멍멍이시바")
                .birthDate(LocalDate.of(2010, 11, 19))
                .owner(owner2.get())
                .type(String.valueOf(Type.시바))
                .build();

        PetRequestDto.CREATE create3 = PetRequestDto.CREATE.builder()
                .name("야옹이러시안블루")
                .birthDate(LocalDate.of(2014, 8, 22))
                .owner(owner2.get())
                .type(String.valueOf(Type.러시안블루))
                .build();

        // 주인 3
        PetRequestDto.CREATE create4 = PetRequestDto.CREATE.builder()
                .name("엉금거북이")
                .birthDate(LocalDate.of(2020, 3, 5))
                .owner(owner3.get())
                .type(String.valueOf(Type.거북이))
                .build();

        PetRequestDto.CREATE create5 = PetRequestDto.CREATE.builder()
                .name("찍찍햄스터")
                .birthDate(LocalDate.of(2018, 12, 7))
                .owner(owner3.get())
                .type(String.valueOf(Type.햄스터))
                .build();

        // 주인 4
        PetRequestDto.CREATE create6 = PetRequestDto.CREATE.builder()
                .name("야옹이먼치킨")
                .birthDate(LocalDate.of(2016, 11, 11))
                .owner(owner4.get())
                .type(String.valueOf(Type.먼치킨))
                .build();

        petService.createPet(create1);
        petService.createPet(create2);
        petService.createPet(create3);
        petService.createPet(create4);
        petService.createPet(create5);
        petService.createPet(create6);

    }

    @Test
    void createVet() {

        List<String> specialties1 = new ArrayList<>();
        specialties1.add("이비인후과");
        specialties1.add("소아과");

        List<String> specialties2 = new ArrayList<>();
        specialties2.add("피부과");
        specialties2.add("흉부외과");
        specialties2.add("마취통증의학과");

        List<String> specialties3 = new ArrayList<>();
        specialties3.add("치과");
        specialties3.add("영상진단과");

        VetRequestDto.CREATE create1 = VetRequestDto.CREATE.builder()
                .firstName("사부")
                .lastName("김")
                .specialtiesName(specialties1)
                .build();

        VetRequestDto.CREATE create2 = VetRequestDto.CREATE.builder()
                .firstName("국종")
                .lastName("이")
                .specialtiesName(specialties2)
                .build();

        VetRequestDto.CREATE create3 = VetRequestDto.CREATE.builder()
                .firstName("명민")
                .lastName("김")
                .specialtiesName(specialties3)
                .build();

        vetService.createVet(create1);
        vetService.createVet(create2);
        vetService.createVet(create3);
    }

    @Test
    void createVisit() {
        Optional<Pet> pet1 = petRepository.findPetByName("멍멍이푸들");
        Optional<Pet> pet2 = petRepository.findPetByName("멍멍이시바");
        Optional<Pet> pet3 = petRepository.findPetByName("야옹이러시안블루");
        Optional<Pet> pet4 = petRepository.findPetByName("엉금거북이");
        Optional<Pet> pet5 = petRepository.findPetByName("찍찍햄스터");
        Optional<Pet> pet6 = petRepository.findPetByName("야옹이먼치킨");

        Optional<Vet> vet1 = vetRepository.findVetByFirstName("사부");
        Optional<Vet> vet2 = vetRepository.findVetByFirstName("국종");
        Optional<Vet> vet3 = vetRepository.findVetByFirstName("명민");

        VisitRequestDto.CREATE create1 = VisitRequestDto.CREATE.builder()
                .visitDate(LocalDate.of(2023, 10, 12))
                .description("건강검진")
                .pet(pet1.get())
                .vet(vet1.get())
                .build();

        VisitRequestDto.CREATE create2 = VisitRequestDto.CREATE.builder()
                .visitDate(LocalDate.of(2023, 11, 22))
                .description("예방접종")
                .pet(pet1.get())
                .vet(vet1.get())
                .build();

        VisitRequestDto.CREATE create3 = VisitRequestDto.CREATE.builder()
                .visitDate(LocalDate.of(2023, 5, 10))
                .description("건강검진 및 예방접종")
                .pet(pet2.get())
                .vet(vet2.get())
                .build();

        VisitRequestDto.CREATE create4 = VisitRequestDto.CREATE.builder()
                .visitDate(LocalDate.of(2023, 12, 2))
                .description("복통 호소 및 구토")
                .pet(pet3.get())
                .vet(vet3.get())
                .build();

        VisitRequestDto.CREATE create5 = VisitRequestDto.CREATE.builder()
                .visitDate(LocalDate.of(2022, 5, 17))
                .description("앞발 절뚝거림")
                .pet(pet3.get())
                .vet(vet3.get())
                .build();

        VisitRequestDto.CREATE create6 = VisitRequestDto.CREATE.builder()
                .visitDate(LocalDate.of(2023, 1, 14))
                .description("숨소리가 거칠고 무기력함")
                .pet(pet4.get())
                .vet(vet1.get())
                .build();

        VisitRequestDto.CREATE create7 = VisitRequestDto.CREATE.builder()
                .visitDate(LocalDate.of(2023, 7, 28))
                .description("예방접종")
                .pet(pet5.get())
                .vet(vet2.get())
                .build();

        VisitRequestDto.CREATE create8 = VisitRequestDto.CREATE.builder()
                .visitDate(LocalDate.of(2023, 10, 5))
                .description("돌에 긁혀 피가남")
                .pet(pet5.get())
                .vet(vet1.get())
                .build();

        VisitRequestDto.CREATE create9 = VisitRequestDto.CREATE.builder()
                .visitDate(LocalDate.of(2023, 9, 14))
                .description("무기력함과 눈꼽이 자주낌")
                .pet(pet6.get())
                .vet(vet2.get())
                .build();

        visitService.createVisit(create1);
        visitService.createVisit(create2);
        visitService.createVisit(create3);
        visitService.createVisit(create4);
        visitService.createVisit(create5);
        visitService.createVisit(create6);
        visitService.createVisit(create7);
        visitService.createVisit(create8);
        visitService.createVisit(create9);

    }

}

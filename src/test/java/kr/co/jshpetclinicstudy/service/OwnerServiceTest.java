package kr.co.jshpetclinicstudy.service;

import kr.co.jshpetclinicstudy.persistence.entity.Owner;
import kr.co.jshpetclinicstudy.persistence.repository.OwnerRepository;
import kr.co.jshpetclinicstudy.service.model.mapper.OwnerMapper;
import kr.co.jshpetclinicstudy.service.model.request.OwnerRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class OwnerServiceTest {

    @InjectMocks
    private OwnerService ownerService;

    @Mock
    private OwnerRepository ownerRepository;

    @Mock
    private OwnerMapper ownerMapper;


    /*
    given - when - then

    given : 테스트에서 구체화하고자 하는 행동을 시작하기 전에 테스트 상태를 설명하는 부분
    when : 구체화하고자 하는 행동
    Then : 어떠한 특정한 행동 때문에 발생할거라고 예상되는 변화에 대한 설명, 검증

     */

    @Test
    void createOwnerTest_SUCCESS() {
        /*//given
        OwnerRequestDto.CREATE create = new OwnerRequestDto.CREATE();
        Owner owner = new Owner();
        Mockito.when(ownerMapper.toEntity(create)).thenReturn(owner);

        //when
        ownerService.createOwner(create);

        //then
        Mockito.verify(ownerMapper, Mockito.times(1)).toEntity(create);
        Mockito.verify(ownerRepository, Mockito.times(1)).save(owner);*/

        OwnerRequestDto.CREATE create = OwnerRequestDto.CREATE.builder()
                .telephone("01012341234")
                .build();

//        doReturn();

    }



}
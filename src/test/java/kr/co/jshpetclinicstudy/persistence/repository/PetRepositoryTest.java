package kr.co.jshpetclinicstudy.persistence.repository;

import kr.co.jshpetclinicstudy.persistence.entity.Owner;
import kr.co.jshpetclinicstudy.persistence.entity.Pet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PetRepositoryTest {

    @Autowired
    private PetRepository petRepository;
    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    void findPetsByOwnerId() {
        List<Pet> pets = petRepository.findPetsByOwnerId(2L);
//        Optional<Owner> owner = ownerRepository.findById(2L);
//        List<Pet> pets = petRepository.findPetsByOwner(owner.get());
        System.out.println(pets);
    }

    @Test
    void findPetByName() {
    }
}
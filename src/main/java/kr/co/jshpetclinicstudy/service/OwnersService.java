package kr.co.jshpetclinicstudy.service;

import kr.co.jshpetclinicstudy.persistence.dto.OwnersDto;
import kr.co.jshpetclinicstudy.persistence.entity.Owners;
import kr.co.jshpetclinicstudy.persistence.repository.OwnersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnersService {

    private final OwnersRepository ownersRepository;

    private Owners dtoToEntity(OwnersDto dto) {
        Owners owners = Owners.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .address(dto.getAddress())
                .city(dto.getCity())
                .telephone(dto.getTelephone())
                .build();

        return owners;
    }

    private OwnersDto entityToDto(Owners owners) {
        OwnersDto dto = OwnersDto.builder()
                .firstName(owners.getFirstName())
                .lastName(owners.getLastName())
                .address(owners.getAddress())
                .city(owners.getCity())
                .telephone(owners.getTelephone())
                .build();

        return dto;
    }

    public void createOwners(OwnersDto dto) {
        Owners owners = dtoToEntity(dto);

        ownersRepository.save(owners);
    }

    public OwnersDto getOwners(Long id) {
        Optional<Owners> result = ownersRepository.findById(id);

        return entityToDto(result.get());
    }

    public void modifyOwners(OwnersDto dto) {
        Optional<Owners> owners = ownersRepository.findById(dto.getOwnerId());

        if (owners.isPresent()) {
//            owners.changeAddress(dto.getAddress());
//            owners.changeCity(dto.getCity());
//            owners.changeTelephone(dto.getTelephone());
            ownersRepository.save(owners.get());
        }
    }

    public void deleteOwners(Long id) {
        Optional<Owners> owners = ownersRepository.findById(id);

        if (owners.isPresent()) {
            ownersRepository.deleteById(id);
        }
    }
}

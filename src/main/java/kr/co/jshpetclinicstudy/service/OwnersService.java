package kr.co.jshpetclinicstudy.service;

import kr.co.jshpetclinicstudy.persistence.entity.Owners;
import kr.co.jshpetclinicstudy.persistence.repository.OwnersRepository;
import kr.co.jshpetclinicstudy.service.model.OwnersRequestDto;
import kr.co.jshpetclinicstudy.service.model.OwnersResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OwnersService {

    private final OwnersRepository ownersRepository;

    public void createOwner(OwnersRequestDto.CREATE create) {
        final Owners owners = Owners.dtoToEntity(create);
        ownersRepository.save(owners);
    }

    public List<OwnersResponseDto.READ> getOwnerList() {
        return ownersRepository.findAll().stream()
                .map(Owners::entityToDto).collect(Collectors.toList());
    }

    public OwnersResponseDto.DETAIL_READ getOwner(Long id) {
        return Owners.entityToDetailDto(ownersRepository.findById(id).get());
    }

    public void updateOwner(OwnersRequestDto.UPDATE update) {
        final Optional<Owners> owners = ownersRepository.findById(update.getOwnerId());
        isOwners(owners);

        owners.get().changeOwnerCity(update.getCity());
        owners.get().changeOwnerAddress(update.getAddress());
        owners.get().changeOwnerTelephone(update.getTelephone());
        owners.get().changeOwnerFirstName(update.getFirstName());
        owners.get().changeOwnerLastName(update.getLastName());

        ownersRepository.save(owners.get());
    }

    public void deleteOwner(Long id) {
        final Optional<Owners> owners = ownersRepository.findById(id);
        isOwners(owners);
        ownersRepository.deleteById(id);
    }

    private void isOwners(Optional<Owners> owners) {
        if (owners.isEmpty()) {
            throw new RuntimeException("This Owner is Not Exist");
        }
    }

}

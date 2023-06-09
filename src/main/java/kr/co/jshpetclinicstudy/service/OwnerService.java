package kr.co.jshpetclinicstudy.service;

import jakarta.transaction.Transactional;
import kr.co.jshpetclinicstudy.infra.exception.DuplicatedException;
import kr.co.jshpetclinicstudy.infra.exception.NotFoundException;
import kr.co.jshpetclinicstudy.infra.model.ResponseStatus;
import kr.co.jshpetclinicstudy.persistence.entity.Owner;
import kr.co.jshpetclinicstudy.persistence.repository.OwnerRepository;
import kr.co.jshpetclinicstudy.persistence.repository.search.OwnerSearchRepository;
import kr.co.jshpetclinicstudy.service.model.mapper.OwnerMapper;
import kr.co.jshpetclinicstudy.service.model.request.OwnerRequestDto;
import kr.co.jshpetclinicstudy.service.model.response.OwnerResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;

    private final OwnerSearchRepository ownerSearchRepository;

    private final OwnerMapper ownerMapper;

    @Transactional
    public void createOwner(OwnerRequestDto.CREATE create) {
        final Owner owner = ownerMapper.toEntity(create);

        isTelephone(create.getTelephone());

        ownerRepository.save(owner);
    }

    // QueryDSL 을 사용하는 방식으로 수정
    public List<OwnerResponseDto.READ> getOwnersByCondition(OwnerRequestDto.CONDITION condition) {
        final List<Owner> owners = ownerSearchRepository.find(condition);

        return owners.stream()
                .map(ownerMapper::toReadDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateOwner(OwnerRequestDto.UPDATE update) {
        final Optional<Owner> owner = ownerRepository.findById(update.getOwnerId());

        isOwner(owner);

        owner.get().updateOwner(update);

        ownerRepository.save(owner.get());

    }

    @Transactional
    public void deleteOwner(Long id) {
        final Optional<Owner> owner = ownerRepository.findById(id);

        isOwner(owner);

        ownerRepository.deleteById(id);
    }

    private void isOwner(Optional<Owner> owner) {
        if (owner.isEmpty()) {
            throw new NotFoundException(ResponseStatus.FAIL_OWNER_NOT_FOUND);
        }
    }

    private void isTelephone(String telephone) {
        if (ownerRepository.existsByTelephone(telephone))
            throw new DuplicatedException(ResponseStatus.FAIL_TELEPHONE_DUPLICATED);
    }
}


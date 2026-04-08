package com.ums.infrastructure.impl;

import com.ums.domain.User;
import com.ums.domain.UserState;
import com.ums.infrastructure.mapper.UserEntityMapper;
import com.ums.infrastructure.persistence.entities.UserEntity;
import com.ums.infrastructure.persistence.repository.RoleRepository;
import com.ums.infrastructure.persistence.repository.UserRepository;
import com.ums.usecases.ports.PasswordEncoderPort;
import com.ums.usecases.ports.UserPersistenceAdapter;
import com.ums.usecases.user.exceptions.UserNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
public class SimpleUserPersistenceAdapter implements UserPersistenceAdapter {
    private final UserRepository userRepository;
    private final UserEntityMapper mapper;
    private final PasswordEncoderPort passwordEncoderPort;
    private final RoleRepository roleRepository;

    @Override
    public boolean existsById(String userId) {
        return userRepository.existsById(userId);
    }

    @Override
    public void add(User u) {
        userRepository.save(
                mapper.toEntity(u)
        );
    }

    @Override
    @Transactional
    public void archive(String userId) {
        UserEntity entity = userRepository.findById(userId)
                .orElseThrow(()->new UserNotFoundException("User cin="+userId+" not found"));
        entity.setState(UserState.ARCHIVED);
        userRepository.save(entity);
    }

    @Override
    @Transactional
    /*  The pondering of '87
    *   Why this method made me think my life choices? xD
    *   User can have one or ALL of his attributes to null.
    *   And WHO IN THEIR RIGHT MIND WOULD LIKE TO CHANGE HIS VALUES TO NULL?
    *
    *   Thought about using validator for dto, but isn't good, using it won't let me modify one field. It would force me to include other unmodified fields in the dto request, which is BAD!
    *   Solution: DTO with Optional<> fields (would test if wrapping with Optional is required or not)
    *   then Map dto to domain (might have null values. Then under here, just use IF not null THEN use SETTER)
    *
    *   Now another problem, how can I proceed with Role??
    *   In dtoDomain mapper, I'll turn it into Role object. If null, don't update
    * */
    public User update(String cin, User u) {
        UserEntity entity = userRepository.findById(cin)
                .orElseThrow(()->new UserNotFoundException("User cin="+cin+" not found"));

        if(Objects.nonNull(u.getUsername())) entity.setUsername(u.getUsername());
        if(Objects.nonNull(u.getPasswd()) && !u.getPasswd().trim().isEmpty()) entity.setPasswdHash(passwordEncoderPort.encodePassword(u.getPasswd()));
        if(Objects.nonNull(u.getState())) entity.setState(u.getState());
        if(Objects.nonNull(u.getRole())) entity.setRole(roleRepository.getReferenceById(u.getRole().getId()));

        return mapper.toDomain(
                userRepository.save(entity)
        );
    }

    @Override
    public Optional<User> findByCin(String cin) {
        return mapper.toOptionalDomain(
                userRepository.findById(cin)
        );
    }

    @Override
    public List<User> findAll() {
        return mapper.toDomainList(
                userRepository.findAll()
        );
    }
}

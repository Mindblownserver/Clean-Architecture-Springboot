package com.kharrat.cleanarchitecturetest.infrastructure.impl;

import com.kharrat.cleanarchitecturetest.infrastructure.mapper.UserEntityMapper;
import com.kharrat.cleanarchitecturetest.infrastructure.persistence.entities.UserEntity;
import com.kharrat.cleanarchitecturetest.infrastructure.persistence.repository.RoleRepository;
import com.kharrat.cleanarchitecturetest.infrastructure.persistence.repository.UserRepository;
import com.kharrat.cleanarchitecturetest.core.domain.User;
import com.kharrat.cleanarchitecturetest.core.domain.UserState;
import com.kharrat.cleanarchitecturetest.core.usecase.ports.PasswordEncoderPort;
import com.kharrat.cleanarchitecturetest.core.usecase.ports.UserPersistenceAdapter;
import com.kharrat.cleanarchitecturetest.core.usecase.user.exceptions.UserNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
public class SimpleUserPersistenceAdapter implements UserPersistenceAdapter {
    private final UserRepository userRepository;
    private final UserEntityMapper mapper;
    private final PasswordEncoderPort passwordEncoderPort;
    private final RoleRepository roleRepository;

    @Override
    public boolean exists(User user) {
        return userRepository.existsById(user.getCin());
    }

    @Override
    public void add(User u) {
        userRepository.save(
                mapper.toEntity(u)
        );
    }

    @Override
    @Transactional
    public void archive(User u) {
        UserEntity entity = userRepository.findById(u.getCin())
                .orElseThrow(()->new UserNotFoundException("User cin="+u.getCin()+" not found"));
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
    *   In dtoDomain mapper, I'll use RoleRepository to map it. If null, don't update
    * */
    public User update(User u) {
        UserEntity entity = userRepository.findById(u.getCin())
                .orElseThrow(()->new UserNotFoundException("User cin="+u.getCin()+" not found"));

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
}

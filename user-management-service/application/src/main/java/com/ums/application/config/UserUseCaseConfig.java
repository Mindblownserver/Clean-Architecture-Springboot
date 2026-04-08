package com.ums.application.config;

import com.ums.infrastructure.impl.BCryptPasswordEncoderPort;
import com.ums.infrastructure.impl.SimpleUserPersistenceAdapter;
import com.ums.infrastructure.mapper.UserEntityMapper;
import com.ums.infrastructure.persistence.repository.RoleRepository;
import com.ums.infrastructure.persistence.repository.UserRepository;
import com.ums.usecases.ports.PasswordEncoderPort;
import com.ums.usecases.ports.UserPersistenceAdapter;
import com.ums.usecases.user.AddUserUseCase;
import com.ums.usecases.user.ArchiveUserUseCase;
import com.ums.usecases.user.GetUsersUseCase;
import com.ums.usecases.user.UpdateUserUseCase;
import com.ums.usecases.user.impl.AddUserUseCaseImpl;
import com.ums.usecases.user.impl.ArchiveUserUseCaseImpl;
import com.ums.usecases.user.impl.GetUsersUseCaseImpl;
import com.ums.usecases.user.impl.UpdateUserUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserUseCaseConfig {
    @Bean
    public PasswordEncoderPort passwordEncoderPort(){
        return new BCryptPasswordEncoderPort();
    }

    @Bean
    public UserPersistenceAdapter userPersistenceAdapter(
             UserRepository userRepo,
             UserEntityMapper mapper,
             PasswordEncoderPort passwordEncoderPort,
             RoleRepository roleRepo
            ){
        return new SimpleUserPersistenceAdapter(
                userRepo, mapper,passwordEncoderPort, roleRepo
        );
    }

    @Bean
    public AddUserUseCase addUserUseCase(final UserPersistenceAdapter userPersistenceAdapter, final PasswordEncoderPort passwordEncoderPort){
        return new AddUserUseCaseImpl(userPersistenceAdapter, passwordEncoderPort);
    }

    @Bean
    public ArchiveUserUseCase archiveUserUseCase(final UserPersistenceAdapter userPersistenceAdapter){
        return new ArchiveUserUseCaseImpl(userPersistenceAdapter);
    }

    @Bean
    public UpdateUserUseCase updateUserUseCase(final UserPersistenceAdapter userPersistenceAdapter){
        return new UpdateUserUseCaseImpl(userPersistenceAdapter);
    }
    @Bean
    public GetUsersUseCase getUsersUseCase(final UserPersistenceAdapter userPersistenceAdapter){
        return new GetUsersUseCaseImpl(userPersistenceAdapter);
    }
}

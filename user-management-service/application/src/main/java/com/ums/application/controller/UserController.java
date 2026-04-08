package com.ums.application.controller;

import com.ums.application.dto.AddUserRequestDTO;
import com.ums.application.dto.UpdateUserRequestDTO;
import com.ums.application.mapper.UserDTOMapper;
import com.ums.domain.User;
import com.ums.usecases.user.AddUserUseCase;
import com.ums.usecases.user.ArchiveUserUseCase;
import com.ums.usecases.user.GetUsersUseCase;
import com.ums.usecases.user.UpdateUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final AddUserUseCase addUserUseCase;
    private final ArchiveUserUseCase archiveUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final GetUsersUseCase getUsersUseCase;
    private final UserDTOMapper mapper;

    @GetMapping
    public List<User> getListOfUsers(){
        return getUsersUseCase.getUsers();
    }

    @PostMapping
    public String addUserRequest(@RequestBody AddUserRequestDTO dto){
        User user = mapper.toDomain(dto);
        return addUserUseCase.add(user);
    }

    @PutMapping("/{cin}")
    public User updateUserRequest(@PathVariable String cin, @RequestBody UpdateUserRequestDTO dto){
        User newUser = mapper.toDomain(dto);
        return updateUserUseCase.update(cin, newUser);
    }

    @PatchMapping("/{cin}")
    public String archiveUserRequest(@PathVariable String cin){
        return archiveUserUseCase.archive(cin);
    }
}

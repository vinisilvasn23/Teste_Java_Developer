package br.com.vinicius.learningspring.service;

import java.util.List;

import br.com.vinicius.learningspring.dto.CreateDepositDto;
import br.com.vinicius.learningspring.dto.UserDto;
import br.com.vinicius.learningspring.model.User;

public interface UserService {

    User createUser(final UserDto userData);

    List<User> readUsers();

    User retrieveUser(final long id);

    User partialUpdateUser(final UserDto userData, final long id);

    void deleteUser(final long id);

    User createDeposit(final CreateDepositDto depositData, final long id);

}
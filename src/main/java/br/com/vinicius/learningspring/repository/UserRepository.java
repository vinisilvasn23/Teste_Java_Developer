package br.com.vinicius.learningspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vinicius.learningspring.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsUserBycpfCnpj(final String cpfCnpj);

    boolean existsUserByEmail(final String email);

}
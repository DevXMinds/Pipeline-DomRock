package com.devxminds.donpipe.service;

import com.devxminds.donpipe.dto.UserDto;
import com.devxminds.donpipe.entidade.User;
import com.devxminds.donpipe.repositorios.PermissaoRepository;
import com.devxminds.donpipe.repositorios.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PermissaoRepository permissaoRepository;

    public UserDto newUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user.setPermissao(permissaoRepository.findById("lz").get());
        user = userRepository.save(user);
        return userDto;
    }

    public UserDto findUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return modelMapper.map(user.get(), UserDto.class);
        } else {
            throw new NoSuchElementException("Usuário não encontrado com i id: " + id);
        }
    }

    public UserDto getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return modelMapper.map(user.get(), UserDto.class);
        } else {
            throw new NoSuchElementException("Nenhum usuário cadastrado com o email: " + email);
        }
    }
}

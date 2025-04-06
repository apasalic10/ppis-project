package ba.unsa.etf.nwt.service;

import ba.unsa.etf.nwt.dto.UserDTO;
import ba.unsa.etf.nwt.entity.User;
import ba.unsa.etf.nwt.exception.ResourceAlreadyExistsException;
import ba.unsa.etf.nwt.exception.ResourceNotFoundException;
import ba.unsa.etf.nwt.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
    }

    public UserDTO getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + id + " not found!"));
        return modelMapper.map(user, UserDTO.class);
    }

    public UserDTO getByEmail (String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException("User with email" + email + " not found!"));
        return modelMapper.map(user, UserDTO.class);
    }

    public User addUser(User user) {
        if(userRepository.existsById(user.getId())){
            throw new ResourceAlreadyExistsException("User already exists!");
        }
        return userRepository.save(user);
    }

    //public User updateUser(User user) {}

    void deleteUser(Long id){
        if(!userRepository.existsById(id)){
            throw new ResourceNotFoundException("User with ID " + id + "not found!");
        }
        userRepository.deleteById(id);
    }
}

package ba.unsa.etf.nwt.service;

import ba.unsa.etf.nwt.dto.TeacherDTO;
import ba.unsa.etf.nwt.dto.UserDTO;
import ba.unsa.etf.nwt.entity.Teacher;
import ba.unsa.etf.nwt.entity.User;
import ba.unsa.etf.nwt.exception.ResourceNotFoundException;
import ba.unsa.etf.nwt.repository.TeacherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final ModelMapper modelMapper;

    public TeacherService(TeacherRepository teacherRepository, ModelMapper modelMapper) {
        this.teacherRepository = teacherRepository;
        this.modelMapper = modelMapper;
    }

    public List<TeacherDTO> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        return teachers.stream().map(teacher -> modelMapper.map(teacher, TeacherDTO.class)).collect(Collectors.toList());
    }

    public TeacherDTO getById(long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher with ID " + id + " not found!"));
        return modelMapper.map(teacher, TeacherDTO.class);
    }
}

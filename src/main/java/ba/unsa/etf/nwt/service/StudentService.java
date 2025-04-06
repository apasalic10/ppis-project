package ba.unsa.etf.nwt.service;

import ba.unsa.etf.nwt.dto.StudentDTO;
import ba.unsa.etf.nwt.dto.UserDTO;
import ba.unsa.etf.nwt.entity.Student;
import ba.unsa.etf.nwt.entity.User;
import ba.unsa.etf.nwt.exception.ResourceNotFoundException;
import ba.unsa.etf.nwt.repository.StudentRepository;
import org.hibernate.metamodel.mapping.ModelPart;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public StudentService(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(student -> modelMapper.map(student, StudentDTO.class)).collect(Collectors.toList());
    }

    public StudentDTO getById(long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with ID " + id + " not found!"));
        return modelMapper.map(student, StudentDTO.class);
    }
}

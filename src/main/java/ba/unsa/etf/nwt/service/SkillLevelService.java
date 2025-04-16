package ba.unsa.etf.nwt.service;

import ba.unsa.etf.nwt.DTO.SkillLevelDTO;
import ba.unsa.etf.nwt.entity.SkillLevel;
import ba.unsa.etf.nwt.repository.SkillLevelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillLevelService {

    private final SkillLevelRepository skillLevelRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SkillLevelService(SkillLevelRepository skillLevelRepository, ModelMapper modelMapper) {
        this.skillLevelRepository = skillLevelRepository;
        this.modelMapper = modelMapper;
    }

    public SkillLevelDTO getSkillLevelById(Long id) {
        SkillLevel skillLevel = skillLevelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SkillLevel with id " + id + " not found"));
        return modelMapper.map(skillLevel, SkillLevelDTO.class);
    }

    public List<SkillLevelDTO> getAllSkillLevels() {
        List<SkillLevel> skillLevels = skillLevelRepository.findAll();
        return skillLevels.stream()
                .map(skillLevel -> modelMapper.map(skillLevel, SkillLevelDTO.class))
                .collect(Collectors.toList());
    }

    public SkillLevelDTO createSkillLevel(SkillLevelDTO skillLevelDTO) {
        SkillLevel skillLevel = modelMapper.map(skillLevelDTO, SkillLevel.class);  // Map DTO to entity
        skillLevel = skillLevelRepository.save(skillLevel);

        return modelMapper.map(skillLevel, SkillLevelDTO.class);
    }
}

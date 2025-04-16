package ba.unsa.etf.nwt.controller;

import ba.unsa.etf.nwt.DTO.SkillLevelDTO;
import ba.unsa.etf.nwt.service.SkillLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/skill-levels")
public class SkillLevelController {

    private final SkillLevelService skillLevelService;

    @Autowired
    public SkillLevelController(SkillLevelService skillLevelService) {
        this.skillLevelService = skillLevelService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillLevelDTO> getSkillLevelById(@PathVariable("id") Long id) {
        try {
            SkillLevelDTO skillLevelDTO = skillLevelService.getSkillLevelById(id);
            return new ResponseEntity<>(skillLevelDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<SkillLevelDTO>> getAllSkillLevels() {
        try {
            List<SkillLevelDTO> skillLevels = skillLevelService.getAllSkillLevels();
            return new ResponseEntity<>(skillLevels, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<SkillLevelDTO> createSkillLevel(@Valid @RequestBody SkillLevelDTO skillLevelDTO) {
        try {
            SkillLevelDTO createdSkillLevel = skillLevelService.createSkillLevel(skillLevelDTO);
            return new ResponseEntity<>(createdSkillLevel, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}

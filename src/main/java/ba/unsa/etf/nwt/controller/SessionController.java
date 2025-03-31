package ba.unsa.etf.nwt.controller;

import ba.unsa.etf.nwt.DTO.ServiceAgreementDTO;
import ba.unsa.etf.nwt.DTO.SessionDTO;
import ba.unsa.etf.nwt.DTO.SessionPostDTO;
import ba.unsa.etf.nwt.entity.ServiceAgreement;
import ba.unsa.etf.nwt.entity.Session;
import ba.unsa.etf.nwt.repository.SessionRepository;
import ba.unsa.etf.nwt.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/session")
public class SessionController {
    private final SessionRepository sessionRepository;
    private final SessionService sessionService;

    @Autowired
    public SessionController(SessionRepository sessionRepository, SessionService sessionService) {
        this.sessionRepository = sessionRepository;
        this.sessionService = sessionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findSessionById(@PathVariable long id) {
        try{
            SessionDTO dto = sessionService.getSessionDTOById(id);
            return ResponseEntity.ok().body(dto);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("Error message",e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<Object> createSession(@RequestBody SessionPostDTO dto) {
        try {
            String createdSession = sessionService.createSession(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdSession);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("Error message",e.getMessage()));
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateSessionField(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {
        try {
            SessionDTO updatedDTO = sessionService.updateSessionField(id, updates);
            return ResponseEntity.ok(updatedDTO);
        }
        catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("Error message", ex.getMessage()));
        }
        catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("Error message", ex.getMessage()));
        }
    }
}

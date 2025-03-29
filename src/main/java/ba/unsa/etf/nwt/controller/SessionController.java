package ba.unsa.etf.nwt.controller;

import ba.unsa.etf.nwt.DTO.SessionDTO;
import ba.unsa.etf.nwt.repository.SessionRepository;
import ba.unsa.etf.nwt.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

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
}

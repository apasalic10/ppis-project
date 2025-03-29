package ba.unsa.etf.nwt.service;

import ba.unsa.etf.nwt.DTO.SessionDTO;
import ba.unsa.etf.nwt.entity.Session;
import ba.unsa.etf.nwt.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    public final SessionRepository sessionRepository;

    @Autowired
    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    private SessionDTO convertToDTO(Session session) {
        return new SessionDTO(
                session.getId(),
                session.getStartTime(),
                session.getEndTime(),
                session.getStatus(),
                (session.getServiceAgreement() != null) ? session.getServiceAgreement().getStatus() : null
        );
    }

    public SessionDTO getSessionDTOById(long id) {
        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Session not found ID: " + id));
        return convertToDTO(session);
    }
}

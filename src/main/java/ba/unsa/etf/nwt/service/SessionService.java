package ba.unsa.etf.nwt.service;

import ba.unsa.etf.nwt.DTO.SessionDTO;
import ba.unsa.etf.nwt.DTO.SessionPostDTO;
import ba.unsa.etf.nwt.entity.ServiceAgreement;
import ba.unsa.etf.nwt.entity.Session;
import ba.unsa.etf.nwt.repository.ServiceAgreementRepository;
import ba.unsa.etf.nwt.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
public class SessionService {
    public final SessionRepository sessionRepository;
    public final ServiceAgreementRepository serviceAgreementRepository;

    @Autowired
    public SessionService(SessionRepository sessionRepository, ServiceAgreementRepository serviceAgreementRepository) {
        this.sessionRepository = sessionRepository;
        this.serviceAgreementRepository = serviceAgreementRepository;
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

    public String createSession(SessionPostDTO dto) {
        // Validate if Service Agreement exists
        ServiceAgreement serviceAgreement = serviceAgreementRepository.findById(dto.getServiceAgreementId())
                .orElseThrow(() -> new RuntimeException("Session not found"));

        if(dto.getStartTime() == null || dto.getEndTime() == null || dto.getStatus() == null){
            throw new IllegalArgumentException("Session has missing parameters");
        }

        // Convert DTO to Entity
        Session session = new Session();
        session.setUuid(UUID.randomUUID().toString()); // Generate a unique UUID
        session.setServiceAgreement(serviceAgreement);
        if (dto.getStartTime().compareTo(dto.getEndTime()) >= 0)
            throw new RuntimeException("End time cannot be greater than start time");
        session.setStartTime(dto.getStartTime());
        session.setEndTime(dto.getEndTime());
        if (!StringUtils.hasText(dto.getStatus()))
            throw new RuntimeException("Session has missing status");
        session.setStatus(dto.getStatus());
        session.setLocation(dto.getLocation());
        session.setMeetingLink(dto.getMeetingLink());
        session.setSessionNotes(dto.getSessionNotes());
        session.setTeacherPreparationNotes(dto.getTeacherPreparationNotes());
        session.setStudentPreparationNotes(dto.getStudentPreparationNotes());

        // Save to database
        return "Session created successfully";
    }

    public SessionDTO updateSessionField(Long id, Map<String, Object> updates) {
        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Session with ID " + id + " not found"));

        // Loop through the provided updates and set the fields dynamically
        updates.forEach((key, value) -> {
            switch (key) {
                case "status" -> session.setStatus(value.toString());
                case "location" -> session.setLocation(value.toString());
                case "meetingLink" -> session.setMeetingLink(value.toString());
                case "sessionNotes" -> session.setSessionNotes(value.toString());
                case "studentPreparationNotes" -> session.setStudentPreparationNotes(value.toString());
                case "teacherPreparationNotes" -> session.setTeacherPreparationNotes(value.toString());
                case "startTime" -> session.setStartTime(Date.from(Instant.parse(value.toString())));
                case "endTime" -> session.setEndTime(Date.from(Instant.parse(value.toString())));
                default -> throw new IllegalArgumentException("Invalid field: " + key);
            }
        });

        // Save updated entity
        Session updatedSession = sessionRepository.save(session);

        return convertToDTO(updatedSession);
    }
}

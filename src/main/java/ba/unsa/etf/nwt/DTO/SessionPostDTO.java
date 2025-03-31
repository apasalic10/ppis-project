package ba.unsa.etf.nwt.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessionPostDTO {
    private long serviceAgreementId;
    private Date startTime;
    private Date endTime;
    private String status;
    private String location;
    private String meetingLink;
    private String sessionNotes;
    private String teacherPreparationNotes;
    private String studentPreparationNotes;
}

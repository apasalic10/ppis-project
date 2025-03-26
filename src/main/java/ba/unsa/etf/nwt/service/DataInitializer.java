package ba.unsa.etf.nwt.service;

import ba.unsa.etf.nwt.entity.*;
import ba.unsa.etf.nwt.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class DataInitializer {

    private final ServiceAgreementRepository serviceAgreementRepository;
    private final InvoiceRepository invoiceRepository;
    private final LearningProgressRepository learningProgressRepository;
    private final PaymentRepository paymentRepository;
    private final SessionRepository sessionRepository;
    private final ReviewRepository reviewRepository;

    public DataInitializer(ServiceAgreementRepository serviceAgreementRepository, InvoiceRepository invoiceRepository, LearningProgressRepository learningProgressRepository, PaymentRepository paymentRepository, SessionRepository sessionRepository, ReviewRepository reviewRepository) {
        this.serviceAgreementRepository = serviceAgreementRepository;
        this.invoiceRepository = invoiceRepository;
        this.learningProgressRepository = learningProgressRepository;
        this.paymentRepository = paymentRepository;
        this.sessionRepository = sessionRepository;
        this.reviewRepository = reviewRepository;
    }

    @PostConstruct
    @Transactional
    public void initData() {
        if (serviceAgreementRepository.count() == 0) {

            ServiceAgreement agreement1 = new ServiceAgreement();
            agreement1.setUuid(UUID.randomUUID().toString());
            agreement1.setTeacherId("teacher_123");
            agreement1.setStudentId("student_456");
            agreement1.setListingId("listing_789");
            agreement1.setStatus("ACTIVE");
            agreement1.setCreationDate(new Date());
            agreement1.setStartDate(new Date());
            agreement1.setEndDate(new Date(System.currentTimeMillis() + 86400000L)); // +1 day
            agreement1.setTerms("Standard agreement terms");
            agreement1.setAutoRenew(false);

            ServiceAgreement agreement2 = new ServiceAgreement();
            agreement2.setUuid(UUID.randomUUID().toString());
            agreement2.setTeacherId("teacher_999");
            agreement2.setStudentId("student_888");
            agreement2.setListingId("listing_777");
            agreement2.setStatus("PENDING");
            agreement2.setCreationDate(new Date());
            agreement2.setStartDate(new Date());
            agreement2.setEndDate(new Date(System.currentTimeMillis() + (7 * 86400000L))); // +7 days
            agreement2.setTerms("Special conditions apply");
            agreement2.setAutoRenew(true);

            // Save agreements first
            serviceAgreementRepository.saveAll(List.of(agreement1, agreement2));

            // Create and attach invoices
            Invoice invoice1 = new Invoice();
            invoice1.setUuid(UUID.randomUUID().toString());
            invoice1.setServiceAgreement(agreement1);
            invoice1.setInvoiceNumber("INV-001");
            invoice1.setIssueDate(new Date());
            invoice1.setDueDate(new Date(System.currentTimeMillis() + (5 * 86400000L))); // +5 days
            invoice1.setTotalAmount(150.75f);
            invoice1.setStatus("PAID");
            invoice1.setPdfDocument(new byte[0]); // Empty PDF placeholder

            Invoice invoice2 = new Invoice();
            invoice2.setUuid(UUID.randomUUID().toString());
            invoice2.setServiceAgreement(agreement2);
            invoice2.setInvoiceNumber("INV-002");
            invoice2.setIssueDate(new Date());
            invoice2.setDueDate(new Date(System.currentTimeMillis() + (10 * 86400000L))); // +10 days
            invoice2.setTotalAmount(250.00f);
            invoice2.setStatus("PENDING");
            invoice2.setPdfDocument(new byte[0]);

            // Save invoices
            invoiceRepository.saveAll(List.of(invoice1, invoice2));

            LearningProgress learningProgress1 = new LearningProgress();
            learningProgress1.setUuid(UUID.randomUUID().toString());
            learningProgress1.setServiceAgreement(agreement1);
            learningProgress1.setStudentId("student_456");
            learningProgress1.setSkillName("Mathematics");
            learningProgress1.setInitialLevel(1);
            learningProgress1.setCurrentLevel(3);
            learningProgress1.setTeacherFeedback("Excellent progress");
            learningProgress1.setStudentNotes("Keep up the good work!");
            learningProgress1.setAssessmentDate(new Date());

            LearningProgress learningProgress2 = new LearningProgress();
            learningProgress2.setUuid(UUID.randomUUID().toString());
            learningProgress2.setServiceAgreement(agreement2);
            learningProgress2.setStudentId("student_888");
            learningProgress2.setSkillName("Physics");
            learningProgress2.setInitialLevel(2);
            learningProgress2.setCurrentLevel(2);
            learningProgress2.setTeacherFeedback("Needs more practice");
            learningProgress2.setStudentNotes("Focus on the basic concepts.");
            learningProgress2.setAssessmentDate(new Date());

            // Save learning progress
            learningProgressRepository.saveAll(List.of(learningProgress1, learningProgress2));

            Session session1 = new Session();
            session1.setUuid(UUID.randomUUID().toString());
            session1.setServiceAgreement(agreement1);
            session1.setStartTime(new Date());
            session1.setEndTime(new Date(System.currentTimeMillis() + 3600000L)); // +1 hour
            session1.setStatus("COMPLETED");
            session1.setLocation("Online");
            session1.setMeetingLink("https://meetinglink.com/12345");
            session1.setSessionNotes("Covered basic algebra concepts.");
            session1.setTeacherPreparationNotes("Prepare algebra exercises.");
            session1.setStudentPreparationNotes("Review basic operations.");

            Session session2 = new Session();
            session2.setUuid(UUID.randomUUID().toString());
            session2.setServiceAgreement(agreement2);
            session2.setStartTime(new Date());
            session2.setEndTime(new Date(System.currentTimeMillis() + (2 * 3600000L))); // +2 hours
            session2.setStatus("PENDING");
            session2.setLocation("Online");
            session2.setMeetingLink("https://meetinglink.com/67890");
            session2.setSessionNotes("Introduction to Newton's laws.");
            session2.setTeacherPreparationNotes("Prepare Newton's law examples.");
            session2.setStudentPreparationNotes("Study the laws of motion.");

// Save sessions
            sessionRepository.saveAll(List.of(session1, session2));

// Create and attach payments
            Payment payment1 = new Payment();
            payment1.setUuid(UUID.randomUUID().toString());
            payment1.setServiceAgreement(agreement1);
            payment1.setSession(session1);
            payment1.setAmount(150.75f);
            payment1.setCurrency("USD");
            payment1.setPaymentDate(new Date());
            payment1.setPaymentMethod("Credit Card");
            payment1.setStatus("COMPLETED");
            payment1.setTransactionId(UUID.randomUUID().toString());
            payment1.setPlatformFee(5.00f);
            payment1.setTeacherAmount(145.75f);

            Payment payment2 = new Payment();
            payment2.setUuid(UUID.randomUUID().toString());
            payment2.setServiceAgreement(agreement2);
            payment2.setSession(session2);
            payment2.setAmount(250.00f);
            payment2.setCurrency("USD");
            payment2.setPaymentDate(new Date());
            payment2.setPaymentMethod("PayPal");
            payment2.setStatus("PENDING");
            payment2.setTransactionId(UUID.randomUUID().toString());
            payment2.setPlatformFee(7.50f);
            payment2.setTeacherAmount(242.50f);

// Save payments
            paymentRepository.saveAll(List.of(payment1, payment2));

            Review review1 = new Review();
            review1.setUuid(UUID.randomUUID().toString());
            review1.setServiceAgreement(agreement1);
            review1.setSession(session1);
            review1.setReviewerId("teacher_123");
            review1.setRevieweeId("student_456");
            review1.setRating(5);
            review1.setComment("Excellent student, very engaged!");
            review1.setSubmissionDate(new Date());
            review1.setPublic(true);
            review1.setRecommended(true);

            Review review2 = new Review();
            review2.setUuid(UUID.randomUUID().toString());
            review2.setServiceAgreement(agreement2);
            review2.setSession(session2);
            review2.setReviewerId("teacher_999");
            review2.setRevieweeId("student_888");
            review2.setRating(3);
            review2.setComment("The student showed some progress but needs improvement.");
            review2.setSubmissionDate(new Date());
            review2.setPublic(true);
            review2.setRecommended(false);

// Save reviews
            reviewRepository.saveAll(List.of(review1, review2));
        }
    }
}

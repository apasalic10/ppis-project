INSERT INTO users ( email, first_name, last_name, password, phone_number, profile_picture, registration_date, role, status, uuid)
VALUES
    ( 'ilhan@gmail.com', 'ilhan', 'ha','password', '123456789', 'admin.jpg', NOW(), 'TEACHER', 'ACTIVE', '550e8400-e29b-41d4-a716-446655440000'),
    ( 'student@example.com', 'John', 'Doe', 'hashed_password', '987654321', 'profile.jpg', NOW(), 'STUDENT', 'ACTIVE', '34d5b2a9-12c6-4e37-aef6-8f1b3792c8d7');

INSERT INTO teachers (credentials, specializations, verification_date, verified, user_id)
VALUES
    (
        'PhD in Computer Science',
        'Software Development, Data Science',
        NOW(),
        TRUE,
        (SELECT id FROM users WHERE email = 'ilhan@gmail.com')
    );

INSERT INTO students (experience, user_id)
values('i have some experience', (select id from users where email = 'student@example.com'));

insert into verifications (document_type, document_url, review_date, reviewer_id, reviewer_notes, status, submission_date, teacher_id)
values ('pdf', 'as/asd', NOW(), 1, 'notes', 'dobar', NOW(),  (SELECT id FROM users WHERE email = 'ilhan@gmail.com'))
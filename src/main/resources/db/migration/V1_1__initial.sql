INSERT INTO users (id, email, first_name, last_name, password_hash, phone_number, profile_picture, registration_date, role, status, uuid)
VALUES
    (DEFAULT, 'admin@example.com', 'Admin', 'User', 'hashed_password', '123456789', 'admin.jpg', NOW(), 'ADMIN', 'ACTIVE', '550e8400-e29b-41d4-a716-446655440000'),
    (DEFAULT, 'user@example.com', 'John', 'Doe', 'hashed_password', '987654321', 'profile.jpg', NOW(), 'ADMIN', 'ACTIVE', '34d5b2a9-12c6-4e37-aef6-8f1b3792c8d7');

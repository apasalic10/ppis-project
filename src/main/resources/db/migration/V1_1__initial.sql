INSERT INTO categories (name, description, icon_url, sort_order, parent_category_id) VALUES
                                                                                         ('Programiranje', 'Učenje različitih jezika programiranja', NULL, 1, NULL),
                                                                                         ('Matematika', 'Pomoć u razumijevanju matematičkih koncepata', NULL, 2, NULL),
                                                                                         ('Jezici', 'Učenje stranih jezika', NULL, 3, NULL),
                                                                                         ('Muzika', 'Sviranje instrumenata i muzička teorija', NULL, 4, NULL),
                                                                                         ('Sport', 'Fizičke aktivnosti i treninzi', NULL, 5, NULL);


INSERT INTO skill_levels (name, description, sort_order) VALUES
                                                             ('Početni', 'Nema prethodnog znanja', 1),
                                                             ('Osnovni', 'Osnovno razumijevanje', 2),
                                                             ('Srednji', 'Srednji nivo vještine', 3),
                                                             ('Napredni', 'Visok nivo znanja', 4),
                                                             ('Ekspert', 'Ekspert nivo', 5);


INSERT INTO tags (name, usage_count) VALUES
                                         ('Java', 10),
                                         ('Python', 15),
                                         ('Algebra', 8),
                                         ('Gitara', 5),
                                         ('Engleski', 12);


INSERT INTO listings (uuid, title, description, creation_date, last_updated, status, price, pricing_model, view_count, featured)
VALUES
    ('1e0b9158-d1f5-4b76-9a77-3f4c4f15e365', 'Nauči Java', 'Za početnike', NOW(), NOW(), 'active', 0, 'free', 100, true),
    ('63b1b3f3-87a7-4c73-9b3d-594c72d14d71', 'Python mentorstvo', 'Radimo projekte', NOW(), NOW(), 'active', 25, 'hourly', 50, false),
    ('c3b47910-b60a-4dd7-9d10-7a42bfcdd82c', 'Gitara osnove', 'Akustična gitara', NOW(), NOW(), 'active', 15, 'fixed', 80, false),
    ('48fc8361-6c3e-43b6-97f6-2f3d60137e59', 'Engleski za putovanja', 'Konverzacijski engleski', NOW(), NOW(), 'active', 10, 'hourly', 30, true),
    ('98f8e6b4-1db3-4bc9-ae46-0b8d08fae20d', 'Algebra pomoć', 'Priprema za ispit', NOW(), NOW(), 'active', 20, 'fixed', 70, false);


INSERT INTO learning_requests (listing_id, learning_goal, preferred_approach, availability_window, urgency_level, group_learning_ok)
VALUES
    ((SELECT id FROM listings WHERE title = 'Nauči Java'), 'Naučiti osnove Jave', 'Online lekcije', 'Vikendom', 3, true),
    ((SELECT id FROM listings WHERE title = 'Gitara osnove'), 'Svirati akorde', 'Fizički susreti', 'Popodne', 2, false),
    ((SELECT id FROM listings WHERE title = 'Algebra pomoć'), 'Savladati kvadratne jednačine', '1-na-1', 'Radnim danima', 4, true),
    ((SELECT id FROM listings WHERE title = 'Python mentorstvo'), 'Naučiti koristiti Python biblioteke', 'Online grupno', 'Vikendom', 1, true),
    ((SELECT id FROM listings WHERE title = 'Engleski za putovanja'), 'Naučiti osnovne fraze', 'Chat + video poziv', 'Fleksibilno', 2, true);


INSERT INTO teaching_offerings (listing_id, teaching_approach, max_students, prerequisites, learning_outcomes, materials, duration_minutes, group_session)
VALUES
    ((SELECT id FROM listings WHERE title = 'Nauči Java'), 'Projektno učenje', 5, 'Nema', 'Razumijevanje Jave', 'Skripte i zadaci', 90, true),
    ((SELECT id FROM listings WHERE title = 'Python mentorstvo'), 'Mentorstvo', 3, 'Osnovno znanje', 'Primjena u projektima', 'Notebook i repo', 60, false),
    ((SELECT id FROM listings WHERE title = 'Gitara osnove'), 'Praktične vježbe', 4, 'Imati gitaru', 'Sviranje osnovnih pjesama', 'PDF vodič', 45, true),
    ((SELECT id FROM listings WHERE title = 'Engleski za putovanja'), 'Simulacija putovanja', 6, 'Osnovni engleski', 'Snalaženje u inostranstvu', 'Video materijali', 30, true),
    ((SELECT id FROM listings WHERE title = 'Algebra pomoć'), 'Rad sa zadacima', 2, 'Osnovna matematika', 'Savladana algebra', 'Priručnik', 50, false);


INSERT INTO listing_categories (listing_id, category_id, assigned_date) VALUES
                                                                            ((SELECT id FROM listings WHERE title = 'Nauči Java'), (SELECT id FROM categories WHERE name = 'Programiranje'), NOW()),
                                                                            ((SELECT id FROM listings WHERE title = 'Python mentorstvo'), (SELECT id FROM categories WHERE name = 'Programiranje'), NOW()),
                                                                            ((SELECT id FROM listings WHERE title = 'Gitara osnove'), (SELECT id FROM categories WHERE name = 'Muzika'), NOW()),
                                                                            ((SELECT id FROM listings WHERE title = 'Engleski za putovanja'), (SELECT id FROM categories WHERE name = 'Jezici'), NOW()),
                                                                            ((SELECT id FROM listings WHERE title = 'Algebra pomoć'), (SELECT id FROM categories WHERE name = 'Matematika'), NOW());


INSERT INTO listing_tags (listing_id, tag_id) VALUES
                                                  ((SELECT id FROM listings WHERE title = 'Nauči Java'), (SELECT id FROM tags WHERE name = 'Java')),
                                                  ((SELECT id FROM listings WHERE title = 'Python mentorstvo'), (SELECT id FROM tags WHERE name = 'Python')),
                                                  ((SELECT id FROM listings WHERE title = 'Gitara osnove'), (SELECT id FROM tags WHERE name = 'Gitara')),
                                                  ((SELECT id FROM listings WHERE title = 'Engleski za putovanja'), (SELECT id FROM tags WHERE name = 'Engleski')),
                                                  ((SELECT id FROM listings WHERE title = 'Algebra pomoć'), (SELECT id FROM tags WHERE name = 'Algebra'));


INSERT INTO listing_skill_levels (listing_id, skill_level_id) VALUES
                                                                  ((SELECT id FROM listings WHERE title = 'Nauči Java'), (SELECT id FROM skill_levels WHERE name = 'Početni')),
                                                                  ((SELECT id FROM listings WHERE title = 'Python mentorstvo'), (SELECT id FROM skill_levels WHERE name = 'Srednji')),
                                                                  ((SELECT id FROM listings WHERE title = 'Gitara osnove'), (SELECT id FROM skill_levels WHERE name = 'Osnovni')),
                                                                  ((SELECT id FROM listings WHERE title = 'Engleski za putovanja'), (SELECT id FROM skill_levels WHERE name = 'Osnovni')),
                                                                  ((SELECT id FROM listings WHERE title = 'Algebra pomoć'), (SELECT id FROM skill_levels WHERE name = 'Napredni'));

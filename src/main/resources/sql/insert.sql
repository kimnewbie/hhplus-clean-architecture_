INSERT INTO lecture (title, instructor, lecture_date, applicant_cnt)
VALUES ('Introduction to Programming', 'John Doe', '2024-01-15', 0),
       ('Advanced Database Systems', 'Jane Smith', '2024-02-01', 0),
       ('Data Structures and Algorithms', 'Michael Johnson', '2024-03-05', 0),
       ('Web Development Basics', 'Alice Brown', '2024-04-10', 0),
       ('Machine Learning 101', 'Robert Lee', '2024-05-20', 0);
INSERT INTO student (name)
VALUES ('Alice Cooper'),
       ('Bob Marley'),
       ('Charlie Daniels'),
       ('David Wright'),
       ('Eve Adams');
INSERT INTO lecture_registration (lecture_id, student_id)
VALUES (1, 1), -- Alice Cooper registers for "Introduction to Programming"
       (2, 2), -- Bob Marley registers for "Advanced Database Systems"
       (3, 3), -- Charlie Daniels registers for "Data Structures and Algorithms"
       (4, 4), -- David Wright registers for "Web Development Basics"
       (5, 5); -- Eve Adams registers for "Machine Learning 101"

#Lecture 테이블
DROP TABLE IF EXISTS lecture_registration;
DROP TABLE IF EXISTS lecture;
DROP TABLE IF EXISTS user;

CREATE TABLE lecture
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    title         VARCHAR(255) NOT NULL,
    instructor    VARCHAR(100) NOT NULL,
    lecture_date  DATE         NOT NULL,
    applicant_cnt INT          NOT NULL DEFAULT 0
);

#User테이블
CREATE TABLE student
(
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

#LectureRegistration 테이블
CREATE TABLE lecture_registration
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    lecture_id BIGINT NOT NULL,
    student_id BIGINT NOT NULL
);

/**
    // ERD DIAGRAM USE : https://dbml.dbdiagram.io/d

    Table lecture {
      id bigint [primary key]
      title varchar [not null]
      instructor varchar [not null]
      lecture_date date [not null]
      applicant_cnt int [default: 0]
    }

    Table user {
      id bigint [primary key]
      name varchar
    }

    Table lecture_registration {
      id bigint [primary key]
      lecture_id bigint [not null]
      user_id bigint [not null]
    }

    Ref: lecture_registration.lecture_id > lecture.id // many-to-one
    Ref: lecture_registration.user_id > user.user_id // many-to-one
 */
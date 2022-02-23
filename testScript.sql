CREATE TABLE tt_curriculum (
	subject_id varchar2(10),
	class_id varchar2(10),
	academy_id varchar2(10),
	start_date DATE,
	end_date DATE,
--	CONSTRAINT tt_curriculum_pk PRIMARY KEY(subject_id,class_id,academy_id)
);

CREATE TABLE tt_testlist (
	testlist_id varchar2(10),
	subject_id varchar2(10),
	class_id varchar2(10),
	academy_id varchar2(10)
--	CONSTRAINT tt_testlist_pk PRIMARY key(testlist_id,subject_id,class_id,academy_id)
--	CONSTRAINT fk1 FOREIGN KEY (subject_id,class_id,academy_id) REFERENCES tt_curriculum (subject_id,class_id,academy_id) ON DELETE CASCADE
);


CREATE TABLE tt_question (
	question_id number(10) NOT null,
	score number(3) NOT null,
	question_content varchar2(100) NOT null,
	answer varchar2(100) NOT NULL,
	testlist_id varchar2(10),
	class_id varchar2(10),
	academy_id varchar2(10)
--	CONSTRAINT tt_question_pk PRIMARY KEY(question_id),
--	CONSTRAINT fk4 FOREIGN key(testlist_id,class_id,academy_id) REFERENCES tt_testlist (testlist_id,class_id,academy_id) ON DELETE CASCADE
);

CREATE TABLE tt_student_answer (
	student_id varchar2(10),
	testlist_id varchar2(10),
	question_id number(10),
	student_answer varchar2(20),
	score number(3),
	class_id varchar2(10),
	academy_id varchar2(10),
	correct_or_not varchar2(2),
	answer_date date
);

INSERT INTO tt_question(question_id,score,question_content,answer,testlist_id,class_id,academy_id) 
SELECT question_seq.nextval,score,question_content,answer,testlist_id,class_id,academy_id FROM TT_QUESTION;

INSERT INTO tt_question VALUES (question_seq.nextval,
            10,
            '테스트문제 생성',
            '테스트 답',
            '테스트1',
            '디컨',
            'human');
           
CREATE SEQUENCE question_seq
       INCREMENT BY 1
       START WITH 1
       MINVALUE 1
       MAXVALUE 9999;
      
DROP SEQUENCE question_seq;

SELECT * FROM TT_TESTLIST;
SELECT * FROM TT_QUESTION;
SELECT * FROM TT_STUDENT_ANSWER;

SELECT QUESTION_ID, student_answer FROM TT_STUDENT_ANSWER WHERE STUDENT_ID = 'test1' AND TESTLIST_ID = '테스트1';
SELECT QUESTION_ID, QUESTION_CONTENT, answer FROM TT_QUESTION WHERE TESTLIST_ID = '테스트1';

DROP TABLE tt_testlist;
DROP TABLE TT_QUESTION;
DROP TABLE TT_STUDENT_ANSWER;

COMMIT;

-- 정답 처리용 쿼리
UPDATE TT_STUDENT_ANSWER 
SET CORRECT_OR_NOT = 'N'
WHERE QUESTION_ID = '3'
AND STUDENT_ID = 'test1';


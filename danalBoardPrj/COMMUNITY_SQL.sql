SELECT * FROM MEMBER;

SELECT * FROM COMMUNITY;

SELECT * FROM 
(SELECT ROWID AS RNUM, T* FROM
(SELECT C.NO, M.NAME, C.TITLE, C.CONTENT, C.ENROLL_DATE, C.DELETE_YN, C.HIT
FROM COMMUNITY C
JOIN MEMBER M ON C.MEMBER_NO = M.NO
WHERE DELETE_YN = 'N'
ORDER BY C.NO DESC)T)
WHERE RNUM BETWEEN 1 AND 4
;

UPDATE COMMUNITY SET TITLE = 'ㅎㅇㅎㅇ', CONTENT = 'ggg' WHERE NO = 1;
UPDATE COMMUNITY SET TITLE = 'ㅎㅇㅎㅇ', CONTENT = 'ggg', MODIFY_DATE = datetime('now', 'localtime') WHERE NO = 5;

--------------------------------------------------------------------------------------------------------

SELECT NO, TITLE, CONTENT, ENROLL_DATE, DELETE_YN, HIT
FROM   COMMUNITY
ORDER BY  NO
LIMIT 5;

SELECT * FROM COMMUNITY;

UPDATE COMMUNITY
SET DELETE_YN = 'Y' 
WHERE NO = 9;

SELECT *, ROWID FROM COMMUNITY;

DROP TABLE COMMUNITY;
CREATE TABLE "COMMUNITY"(
	"NO"	INTEGER PRIMARY KEY AUTOINCREMENT,
	"MEMBER_NO"	INTEGER,
	"TITLE"	TEXT NOT NULL,
	"CONTENT"	TEXT NOT NULL,
	"ENROLL_DATE"	TEXT NOT NULL DEFAULT datetime,
	"MODIFY_DATE" TEXT NOT NULL DEFAULT datetime,
	"HIT"	INTEGER DEFAULT 0,
	"DELETE_YN"	TEXT NOT NULL DEFAULT "N",
	FOREIGN KEY("MEMBER_NO")REFERENCES MEMBER("NO") ON DELETE CASCADE
);

COMMIT;

-- ROWNUM 이 맨 뒤에 나오는 SQL문 
select *, rowid from COMMUNITY where rowid between 1 and 10;

-- ROWNUM 을 맨 앞으로 두는 SQL문
SELECT ROWID, NO, TITLE, CONTENT, MEMBER_NO, ENROLL_DATE, HIT FROM COMMUNITY WHERE ROWID BETWEEN 1 AND 10;

-- ROWID 와 JOIN 을 같이 쓸 경우 오류가 난다 -> 확인 필요
SELECT ROWID, COMMUNITY.NO, MEMBER.NAME, COMMUNITY.TITLE, COMMUNITY.CONTENT, COMMUNITY.ENROLL_DATE, COMMUNITY.DELETE_YN, COMMUNITY.HIT
FROM COMMUNITY
INNER JOIN MEMBER ON COMMUNITY.MEMBER_NO = MEMBER.NO
WHERE ROWID BETWEEN 1 AND 10;

-- 서브쿼리 형식으로 작성하면 ROWID 값이 NULL 이 된다
SELECT *, ROWID FROM(SELECT COMMUNITY.NO, MEMBER.NAME, COMMUNITY.TITLE, COMMUNITY.CONTENT, COMMUNITY.ENROLL_DATE, COMMUNITY.DELETE_YN, COMMUNITY.HIT FROM COMMUNITY INNER JOIN MEMBER ON COMMUNITY.MEMBER_NO = MEMBER.NO);

-- 해결 방법***
SELECT *,
       (SELECT rowid
          FROM COMMUNITY
         WHERE COMMUNITY.NO = C.NO) AS CUSTOM_ROWID
 FROM (SELECT COMMUNITY.NO,
               MEMBER.NAME,
               COMMUNITY.TITLE,
               COMMUNITY.CONTENT,
               COMMUNITY.ENROLL_DATE,
               COMMUNITY.DELETE_YN,
               COMMUNITY.HIT
FROM COMMUNITY
INNER JOIN MEMBER
ON COMMUNITY.MEMBER_NO = MEMBER.NO) AS C;

-----------------------------------------------------
-- 원하는 ROWID 갯수 조회하기**
-----------------------------------------------------
-- 5까지 구하기
SELECT *,
       (SELECT rowid
          FROM COMMUNITY
         WHERE COMMUNITY.NO = C.NO) AS CUSTOM_ROWID
 FROM (SELECT COMMUNITY.NO,
               MEMBER.NAME,
               COMMUNITY.TITLE,
               COMMUNITY.CONTENT,
               COMMUNITY.ENROLL_DATE,
               COMMUNITY.DELETE_YN,
               COMMUNITY.HIT
FROM COMMUNITY
INNER JOIN MEMBER
ON COMMUNITY.MEMBER_NO = MEMBER.NO) AS C
LIMIT 5;

-- 6,7 만 구하기
SELECT *,
       (SELECT rowid
          FROM COMMUNITY
         WHERE COMMUNITY.NO = C.NO) AS CUSTOM_ROWID
 FROM (SELECT COMMUNITY.NO,
               MEMBER.NAME,
               COMMUNITY.TITLE,
               COMMUNITY.CONTENT,
               COMMUNITY.ENROLL_DATE,
               COMMUNITY.DELETE_YN,
               COMMUNITY.HIT
FROM COMMUNITY
INNER JOIN MEMBER
ON COMMUNITY.MEMBER_NO = MEMBER.NO) AS C
LIMIT 7 OFFSET 5;

--------------------------------------------
--ROWID 를 BETWEEN 1 AND 10 처럼 쓰기
--------------------------------------------
SELECT *,
       (SELECT rowid
          FROM COMMUNITY
         WHERE COMMUNITY.NO = C.NO
         LIMIT 1) AS CUSTOM_ROWID
 FROM (SELECT COMMUNITY.NO,
               MEMBER.NAME,
               COMMUNITY.TITLE,
               COMMUNITY.CONTENT,
               COMMUNITY.ENROLL_DATE,
               COMMUNITY.DELETE_YN,
               COMMUNITY.HIT
FROM COMMUNITY
INNER JOIN MEMBER
ON COMMUNITY.MEMBER_NO = MEMBER.NO) AS C
LIMIT 10 OFFSET 0;

--------------------------------------------
--ROWID 를 BETWEEN 10 AND 20 처럼 쓰기
--------------------------------------------
SELECT *,
       (SELECT rowid
          FROM COMMUNITY
         WHERE COMMUNITY.NO = C.NO
         LIMIT 1) AS CUSTOM_ROWID
 FROM (SELECT COMMUNITY.NO,
               MEMBER.NAME,
               COMMUNITY.TITLE,
               COMMUNITY.CONTENT,
               COMMUNITY.ENROLL_DATE,
               COMMUNITY.DELETE_YN,
               COMMUNITY.HIT
FROM COMMUNITY
INNER JOIN MEMBER
ON COMMUNITY.MEMBER_NO = MEMBER.NO) AS C
LIMIT 10 OFFSET 9;

--앞에 LIMIT 1을 쓰는 이유는 
------------------------------------------------------------


-- INSERT 더미데이터
INSERT INTO COMMUNITY (MEMBER_NO, TITLE, CONTENT, ENROLL_DATE, MODIFY_DATE)VALUES('2', '안녕하세요', '내용입니다~', datetime('now', 'localtime'), datetime('now', 'localtime'));
INSERT INTO COMMUNITY (MEMBER_NO, TITLE, CONTENT, ENROLL_DATE, MODIFY_DATE)VALUES('2', '22222', '내용입니다~', datetime('now', 'localtime'), datetime('now', 'localtime'));
INSERT INTO COMMUNITY (MEMBER_NO, TITLE, CONTENT, ENROLL_DATE, MODIFY_DATE)VALUES('3', '33333', '내용입니다~', datetime('now', 'localtime'), datetime('now', 'localtime'));
INSERT INTO COMMUNITY (MEMBER_NO, TITLE, CONTENT, ENROLL_DATE, MODIFY_DATE)VALUES('3', '44444', '내용입니다~', datetime('now', 'localtime'), datetime('now', 'localtime'));
INSERT INTO COMMUNITY (MEMBER_NO, TITLE, CONTENT, ENROLL_DATE, MODIFY_DATE)VALUES('3', '55555', '내용입니다~', datetime('now', 'localtime'), datetime('now', 'localtime'));
INSERT INTO COMMUNITY (MEMBER_NO, TITLE, CONTENT, ENROLL_DATE, MODIFY_DATE)VALUES('4', '66666', '내용입니다~', datetime('now', 'localtime'), datetime('now', 'localtime'));
INSERT INTO COMMUNITY (MEMBER_NO, TITLE, CONTENT, ENROLL_DATE, MODIFY_DATE)VALUES('4', '77777', '내용입니다~', datetime('now', 'localtime'), datetime('now', 'localtime'));

COMMIT;

SELECT * FROM COMMUNITY;

SELECT * FROM MEMBER;

SELECT COUNT (*) AS CNT FROM COMMUNITY WHERE DELETE_YN = 'N';

SELECT ROWID, NO, MEMBER_NO, TITLE, CONTENT, ENROLL_DATE, DELETE_YN, HIT FROM COMMUNITY WHERE ROWID BETWEEN 1 AND 10;

-------------------------------------
--게시글 수정하는 구문
-------------------------------------
UPDATE COMMUNITY
			SET TITLE = '바뀐 제목ㅎㅎ'
			    ,CONTENT = '바뀐 내용!!'
			    ,DELETE_YN = 'N'
			    ,ENROLL_DATE = datetime('now', 'localtime')
			WHERE NO = 1;
			
UPDATE COMMUNITY SET TITLE = 'DB에서 제목 바꿈', CONTENT = '여기도 바꿈' , MODIFY_DATE = datetime('now', 'localtime') WHERE NO = 5;

UPDATE COMMUNITY SET TITLE = '되나?' WHERE NO = 5;
COMMIT;

SELECT * FROM COMMUNITY;

SELECT *,(SELECT rowid FROM COMMUNITY 
WHERE COMMUNITY.NO = C.NO) AS CUSTOM_ROWID 
FROM (SELECT COMMUNITY.NO, MEMBER.NAME, COMMUNITY.MEMBER_NO, MEMBER.NO, COMMUNITY.TITLE, 
COMMUNITY.CONTENT, COMMUNITY.ENROLL_DATE, COMMUNITY.DELETE_YN, 
COMMUNITY.HIT FROM COMMUNITY
INNER JOIN MEMBER
ON COMMUNITY.MEMBER_NO = MEMBER.NO) AS C
WHERE DELETE_YN = 'N';



UPDATE COMMUNITY 
SET DELETE_YN = 'Y' 
WHERE NO = '8';
			
COMMIT;
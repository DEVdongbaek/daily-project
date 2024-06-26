-- 모든 제약 조건 비활성화
SET REFERENTIAL_INTEGRITY FALSE;
truncate table member;
truncate table member_roles;

truncate table daily;
truncate table daily_content;
truncate table daily_like;

SET REFERENTIAL_INTEGRITY TRUE;
-- 모든 제약 조건 활성화


-- Member
INSERT INTO member (nick_name, password, user_name) VALUES ('반갑티비', 1234, 'kevin');
INSERT INTO member_roles (member_id,roles) VALUES (1, 'USER');

INSERT INTO member (nick_name, password, user_name) VALUES ('안녕티비', 1234, 'demo');
INSERT INTO member_roles (member_id,roles) VALUES (2, 'USER');

INSERT INTO member (nick_name, password, user_name) VALUES ('어쩔티비', 1234, 'yardyard');
INSERT INTO member_roles (member_id,roles) VALUES (3, 'USER');

INSERT INTO member (nick_name, password, user_name) VALUES ('어쩔티비', 1234, 'hello');
INSERT INTO member_roles (member_id,roles) VALUES (4, 'USER');


-- Daily
INSERT INTO daily (is_public, member_id) VALUES (true, 1);
INSERT INTO daily_like (daily_id) VALUES (1);
INSERT INTO daily_content (daily_id,penitence1,penitence2,penitence3,thanks1,thanks2,thanks3) VALUES (1, '회개1', '회개2', '회개3', '감사1', '감사2', '감사3');

INSERT INTO daily (is_public, member_id) VALUES (true, 1);
INSERT INTO daily_like (daily_id) VALUES (2);
INSERT INTO daily_content (daily_id,penitence1,penitence2,penitence3,thanks1,thanks2,thanks3) VALUES (2, '회개1', '회개2', '회개3', '감사1', '감사2', '감사3');

INSERT INTO daily (is_public, member_id) VALUES (true, 1);
INSERT INTO daily_like (daily_id) VALUES (3);
INSERT INTO daily_content (daily_id,penitence1,penitence2,penitence3,thanks1,thanks2,thanks3) VALUES (3, '회개1', '회개2', '회개3', '감사1', '감사2', '감사3');

INSERT INTO daily (is_public, member_id) VALUES (true, 1);
INSERT INTO daily_like (daily_id) VALUES (4);
INSERT INTO daily_content (daily_id,penitence1,penitence2,penitence3,thanks1,thanks2,thanks3) VALUES (4, '회개1', '회개2', '회개3', '감사1', '감사2', '감사3');

INSERT INTO daily (is_public, member_id) VALUES (true, 1);
INSERT INTO daily_like (daily_id) VALUES (5);
INSERT INTO daily_content (daily_id,penitence1,penitence2,penitence3,thanks1,thanks2,thanks3) VALUES (5, '회개1', '회개2', '회개3', '감사1', '감사2', '감사3');

INSERT INTO daily (is_public, member_id) VALUES (true, 2);
INSERT INTO daily_like (daily_id) VALUES (6);
INSERT INTO daily_content (daily_id,penitence1,penitence2,penitence3,thanks1,thanks2,thanks3) VALUES (6, '회개1', '회개2', '회개3', '감사1', '감사2', '감사3');

INSERT INTO daily (is_public, member_id) VALUES (true, 2);
INSERT INTO daily_like (daily_id) VALUES (7);
INSERT INTO daily_content (daily_id,penitence1,penitence2,penitence3,thanks1,thanks2,thanks3) VALUES (7, '회개1', '회개2', '회개3', '감사1', '감사2', '감사3');

INSERT INTO daily (is_public, member_id) VALUES (true, 3);
INSERT INTO daily_like (daily_id) VALUES (8);
INSERT INTO daily_content (daily_id,penitence1,penitence2,penitence3,thanks1,thanks2,thanks3) VALUES (8, '회개1', '회개2', '회개3', '감사1', '감사2', '감사3');

INSERT INTO daily (is_public, member_id) VALUES (true, 3);
INSERT INTO daily_like (daily_id) VALUES (9);
INSERT INTO daily_content (daily_id,penitence1,penitence2,penitence3,thanks1,thanks2,thanks3) VALUES (9, '회개1', '회개2', '회개3', '감사1', '감사2', '감사3');

INSERT INTO daily (is_public, member_id) VALUES (true, 4);
INSERT INTO daily_like (daily_id) VALUES (10);
INSERT INTO daily_content (daily_id,penitence1,penitence2,penitence3,thanks1,thanks2,thanks3) VALUES (10, '회개1', '회개2', '회개3', '감사1', '감사2','감사');






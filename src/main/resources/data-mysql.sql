INSERT INTO `syr_dev`.`syr_users`
(`user_id`,
`add_date`,
`business_id`,
`dob`,
`email_verfication_status`,
`employment_status`,
`first_name`,
`gender`,
`guest_rate_count`,
`guest_rating`,
`guest_review_count`,
`host_rate_count`,
`host_rating`,
`host_review_count`,
`last_name`,
`marital_status`,
`mob_verfication_status`,
`mod_date`,
`primary_email`,
`primary_mob_country_code`,
`primary_mobile_no`,
`reg_channel`,
`user_block_count`,
`user_previlage`,
`user_status`,
`user_type`)
VALUES
(1001,
sysdate(),
'52943542-d06b-4ce7-a87d-5c06c1751d5e',
'1900-01-01',
'P',
'E',
'Ebin',
'M',
0,
0,
0,
0,
0,
0,
'Sebastian',
'S',
'P',
sysdate(),
'ebinms@gmail.com',
91,
9633372914,
'IOS',
0,
'NU',
'A',
'AD')
ON DUPLICATE KEY UPDATE mod_date = sysdate();

INSERT INTO `syr_dev`.`syr_user_phones`
(`phone_no`,
`add_date`,
`business_id`,
`country_code`,
`dnd_status`,
`mod_date`,
`phone_status`,
`user_id`)
VALUES
(9633372914,
sysdate(),
'34412e8d-dc6f-44d9-8c29-d65d6c16ab88',
91,
'Y',
sysdate(),
'A',
1001)
ON DUPLICATE KEY UPDATE mod_date = sysdate();

INSERT INTO `syr_dev`.`syr_user_emails`
(`email_id`,
`add_date`,
`business_id`,
`dnd_status`,
`email_status`,
`mod_date`,
`user_id`)
VALUES
('ebinms@gmail.com',
sysdate(),
'1a28fff3-d831-4ee1-889d-27b9daeba702',
'Y',
'A',
sysdate(),
1001)
ON DUPLICATE KEY UPDATE mod_date = sysdate();

INSERT INTO `syr_dev`.`syr_user_logins`
(`user_name`,
`add_date`,
`business_id`,
`block_status`,
`last_login`,
`last_password_reset`,
`mod_date`,
`password`,
`user_id`)
VALUES
('9633372914',
sysdate(),
'd5edf953-06c5-429a-9e8e-7c0943451a7d',
'N',
sysdate(),
sysdate(),
sysdate(),
'$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu',
'1001')
ON DUPLICATE KEY UPDATE mod_date = sysdate();

INSERT INTO `syr_dev`.`syr_user_logins`
(`user_name`,
`add_date`,
`business_id`,
`block_status`,
`last_login`,
`last_password_reset`,
`mod_date`,
`password`,
`user_id`)
VALUES
('ebinms@gmail.com',
sysdate(),
'2991d08c-1a13-438d-9975-8a2fbd5599ed',
'N',
sysdate(),
sysdate(),
sysdate(),
'$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu',
'1001')
ON DUPLICATE KEY UPDATE mod_date = sysdate();

INSERT INTO `syr_dev`.`syr_client_master`
(`client_id`,
`add_date`,
`business_id`,
`client_name`,
`client_password`,
`mod_date`,
`client_email`,
`contact_other`,
`country_code`,
`mobile_number`)
VALUES
(1001,
sysdate(),
'53199215-4061-4af7-a415-559329cdbf95',
'SYR Application',
'$2a$10$o5OpHENOVtAnixGZyJ6AgecJWe2yQ0VV2sAcLpz7LP1mxQz/sUYCC',
sysdate(),
'ebinms@gmail.com',
0,
91,
9633372914)
ON DUPLICATE KEY UPDATE mod_date = sysdate();


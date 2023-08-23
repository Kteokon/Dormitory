INSERT INTO `application` (`id`, `date`, `type`, `student_id`) VALUES (1, '2022-09-02', 'Assigning', 1);
INSERT INTO `application` (`id`, `date`, `type`, `student_id`) VALUES (4, '2022-09-02', 'Assigning', 6);
INSERT INTO `application` (`id`, `date`, `type`, `student_id`) VALUES (5, '2022-09-02', 'Eviction', 7);

----------------------------------------------------------

INSERT INTO `auto_user` (`id`, `password`, `role`, `username`, `student_id`) VALUES (1, 'pass', 'ROLE_ADMIN', 'admin', NULL);
INSERT INTO `auto_user` (`id`, `password`, `role`, `username`, `student_id`) VALUES (2, 'pass', 'ROLE_USER', 'ed_00001', 1);
INSERT INTO `auto_user` (`id`, `password`, `role`, `username`, `student_id`) VALUES (3, 'pass', 'ROLE_USER', 'ed_00002', 2);
INSERT INTO `auto_user` (`id`, `password`, `role`, `username`, `student_id`) VALUES (4, 'pass', 'ROLE_USER', 'ed_00003', 3);
INSERT INTO `auto_user` (`id`, `password`, `role`, `username`, `student_id`) VALUES (5, 'pass', 'ROLE_USER', 'ed_00004', 4);
INSERT INTO `auto_user` (`id`, `password`, `role`, `username`, `student_id`) VALUES (6, 'pass', 'ROLE_USER', 'ed_00005', 5);
INSERT INTO `auto_user` (`id`, `password`, `role`, `username`, `student_id`) VALUES (7, 'pass', 'ROLE_USER', 'ed_00006', 6);
INSERT INTO `auto_user` (`id`, `password`, `role`, `username`, `student_id`) VALUES (8, 'pass', 'ROLE_USER', 'ed_00007', 7);
INSERT INTO `auto_user` (`id`, `password`, `role`, `username`, `student_id`) VALUES (9, 'pass', 'ROLE_USER', 'ed_00008', 8);
INSERT INTO `auto_user` (`id`, `password`, `role`, `username`, `student_id`) VALUES (10, 'pass', 'ROLE_USER', 'ed_00009', 9);
INSERT INTO `auto_user` (`id`, `password`, `role`, `username`, `student_id`) VALUES (11, 'pass', 'ROLE_USER', 'ed_00010', 10);

----------------------------------------------------------

INSERT INTO `building` (`id`, `address`, `number`) VALUES (1, 'Irkutsk, 25 Oktyabrya str., 25', 'Dorm #1');

----------------------------------------------------------

INSERT INTO `room` (`id`, `gender`, `is_full`, `number`, `size`, `building_id`) VALUES (1, 'f', b'0', 'Room #101', 4, 1);
INSERT INTO `room` (`id`, `gender`, `is_full`, `number`, `size`, `building_id`) VALUES (2, 'm', b'0', 'Room #102', 3, 1);
INSERT INTO `room` (`id`, `gender`, `is_full`, `number`, `size`, `building_id`) VALUES (3, 'i', b'0', 'Room #103', 6, 1);
INSERT INTO `room` (`id`, `gender`, `is_full`, `number`, `size`, `building_id`) VALUES (4, 'i', b'0', 'Room #201', 2, 1);

----------------------------------------------------------

INSERT INTO `student` (`id`, `application`, `email`, `gender`, `name`, `phone_number`, `group_id`, `room_id`) VALUES (1, b'1', 'kozlov@gmail.com', 'm', 'Kozlov Ivan Lvovich', '+7 (073)533-04-40', 1, NULL);
INSERT INTO `student` (`id`, `application`, `email`, `gender`, `name`, `phone_number`, `group_id`, `room_id`) VALUES (2, b'0', 'chernov@gmail.com', 'm', 'Chernov Vladislav Ruslanov', '+7 (597)398-93-48', 1, 2);
INSERT INTO `student` (`id`, `application`, `email`, `gender`, `name`, `phone_number`, `group_id`, `room_id`) VALUES (3, b'0', 'kazakov@gmail.com', 'm', 'Kazakov Maksim Konstantinovich', '+7 (377)317-35-24', 2, NULL);
INSERT INTO `student` (`id`, `application`, `email`, `gender`, `name`, `phone_number`, `group_id`, `room_id`) VALUES (4, b'0', 'mishin@gmail.com', 'm', 'Mishin Yaroslav Sergeevich', '+7 (485)603-59-28', 1, NULL);
INSERT INTO `student` (`id`, `application`, `email`, `gender`, `name`, `phone_number`, `group_id`, `room_id`) VALUES (5, b'0', 'arhipov@gmail.com', 'm', 'Arhipov Leonard Petrovich', '+7 (536)636-07-77', 2, 2);
INSERT INTO `student` (`id`, `application`, `email`, `gender`, `name`, `phone_number`, `group_id`, `room_id`) VALUES (6, b'1', 'safonov@gmail.com', 'm', 'Safonov Felix Vladislavovich', '+7 (245)549-50-08', 1, NULL);
INSERT INTO `student` (`id`, `application`, `email`, `gender`, `name`, `phone_number`, `group_id`, `room_id`) VALUES (7, b'1', 'kazakova@gmail.com', 'f', 'Kazakova Ksenia Tihonovna', '+7 (320)568-10-42', 2, 1);
INSERT INTO `student` (`id`, `application`, `email`, `gender`, `name`, `phone_number`, `group_id`, `room_id`) VALUES (8, b'0', 'pavlova@gmail.com', 'f', 'Pavlova Elena Fedorovna', '+7 (020)844-35-42', 1, 1);
INSERT INTO `student` (`id`, `application`, `email`, `gender`, `name`, `phone_number`, `group_id`, `room_id`) VALUES (9, b'0', 'tarasova@gmail.com', 'f', 'Tarasova Agatha Fedorovna', '+7 (575)602-42-29', 2, NULL);
INSERT INTO `student` (`id`, `application`, `email`, `gender`, `name`, `phone_number`, `group_id`, `room_id`) VALUES (10, b'0', 'ilina@gmail.com', 'f', 'Ilina Olga Antonovna', '+7 (764)798-23-27', 2, 1);

----------------------------------------------------------

INSERT INTO `student_group` (`id`, `name`) VALUES (1, '02361-DB');
INSERT INTO `student_group` (`id`, `name`) VALUES (2, '03141-DB');
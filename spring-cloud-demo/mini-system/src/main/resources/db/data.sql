-- 测试数据
INSERT INTO sys_role (id, name, description) VALUES (1, 'ROLE_ADMIN', '管理员');
INSERT INTO sys_role (id, name, description) VALUES (2, 'ROLE_TEST', '测试');
INSERT INTO sys_user (id, username, password) VALUES (1, 'admin', '$2a$10$xLH.pDNz3d2frOBQ6Gc.wuHY4ghwlSyFDgy0Ta.psXmm1YJjNaV1G');
INSERT INTO sys_user (id, username, password) VALUES (2, 'test', '$2a$10$xLH.pDNz3d2frOBQ6Gc.wuHY4ghwlSyFDgy0Ta.psXmm1YJjNaV1G');
INSERT INTO sys_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO sys_user_role (user_id, role_id) VALUES (2, 2);

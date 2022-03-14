-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '风铃玉秀', '18905051289', '深圳南山区', 1, '风铃玉秀', '$2a$10$xvEChGHTxTveiuVdHw3z8.k0dQo.pwV6pH7Vz6nZwCXhjhVAsdfYO', NULL, '', '2022-03-14 14:55:13', '2022-03-14 15:42:46');
INSERT INTO `sys_user` VALUES (2, '清风', '15396538192', '北京', 1, '清风', '$2a$10$xvEChGHTxTveiuVdHw3z8.k0dQo.pwV6pH7Vz6nZwCXhjhVAsdfYO', NULL, NULL, '2022-03-14 14:55:58', '2022-03-14 15:42:50');
-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'admin', '管理员', '2022-03-14 15:20:55', '2022-03-14 15:20:55');
INSERT INTO `role` VALUES (2, 'developer', '开发者', '2022-03-14 15:22:54', '2022-03-14 15:22:54');
-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES (1, '/api/develop', NULL, NULL, NULL, NULL, NULL, 1, '2022-03-14 15:29:42', '2022-03-14 15:33:29');
INSERT INTO `resource` VALUES (2, '/api/admin', NULL, NULL, NULL, NULL, NULL, 1, '2022-03-14 15:33:21', '2022-03-14 15:33:21');
INSERT INTO `resource` VALUES (3, '/api/develop/1', NULL, NULL, NULL, NULL, 1, 1, '2022-03-14 15:33:46', '2022-03-14 15:33:46');
-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1, 1, '2022-03-14 15:28:25', '2022-03-14 15:28:25');
INSERT INTO `user_role` VALUES (2, 2, 2, '2022-03-14 15:28:39', '2022-03-14 15:28:39');
-- ----------------------------
-- Records of role_resource
-- ----------------------------
INSERT INTO `role_resource` VALUES (1, 1, 1, '2022-03-14 15:44:02', '2022-03-14 15:44:02');
INSERT INTO `role_resource` VALUES (2, 1, 2, '2022-03-14 15:44:08', '2022-03-14 15:44:08');
INSERT INTO `role_resource` VALUES (3, 1, 3, '2022-03-14 15:44:14', '2022-03-14 15:44:14');
INSERT INTO `role_resource` VALUES (4, 2, 3, '2022-03-14 15:44:28', '2022-03-14 15:44:37');
-- 初始化角色
INSERT INTO `t_role` (`name`, `create_time`, `update_time`)
VALUES ("SUPERADMIN", now(), now());
INSERT INTO `t_role` (`name`, `create_time`, `update_time`)
VALUES ("ADMIN", now(), now());

-- 初始化管理员用户
INSERT INTO `t_admin` (`username`, `password`, `create_time`, `update_time`)
VALUES ("superadmin", "00d6e041a5df5fbe0a33c15adfda50b77d56a29e8c6c999e30ac12eaa7dbf3b39b2060036f9531f5", now(), now());
INSERT INTO `t_admin` (`username`, `password`, `create_time`, `update_time`)
VALUES ("admin", "00d6e041a5df5fbe0a33c15adfda50b77d56a29e8c6c999e30ac12eaa7dbf3b39b2060036f9531f5", now(), now());

-- 关联用户角色
INSERT INTO t_admin_role(admin_id, role_id)
SELECT t_admin.id, t_role.id FROM t_admin, t_role WHERE t_admin.username = "superadmin" AND t_role.name = "SUPERADMIN";
INSERT INTO t_admin_role(admin_id, role_id)
SELECT t_admin.id, t_role.id FROM t_admin, t_role WHERE t_admin.username = "admin" AND t_role.name = "ADMIN";

-- 初始化资源
INSERT INTO `t_resource` (`name`, `address`, `create_time`, `update_time`)
VALUES ("index", "/", now(), now());

-- 关联资源角色
INSERT INTO t_role_resource(role_id, resource_id)
SELECT t_role.id, t_resource.id FROM t_role, t_resource WHERE t_role.name = "ADMIN" AND t_resource.name = "index";
INSERT INTO t_role_resource(role_id, resource_id)
SELECT t_role.id, t_resource.id FROM t_role, t_resource WHERE t_role.name = "SUPERADMIN" AND t_resource.name = "index";
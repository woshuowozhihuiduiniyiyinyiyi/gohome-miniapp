# 1.修改业主添加相关微信字段
ALTER TABLE `owner`
ADD COLUMN `province`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '省' AFTER `wx_nickname`,
ADD COLUMN `city`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '市' AFTER `province`,
ADD COLUMN `avatar_url`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '头像' AFTER `city`,
ADD COLUMN `open_id`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '微信的open_id' AFTER `avatar_url`;


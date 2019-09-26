-- ----------------------------
-- Function structure for findVillageChildren
-- ----------------------------
DROP FUNCTION IF EXISTS `findVillageChildren`;
delimiter ;;
CREATE FUNCTION `findVillageChildren`(rootId INT)
 RETURNS varchar(4000) CHARSET utf8
BEGIN
    DECLARE sTemp VARCHAR(4000);
    DECLARE sTempChd VARCHAR(4000);
    SET sTemp = '$';
    SET sTempChd = CAST(rootId as CHAR);
    WHILE sTempChd is not null DO
        SET sTemp = CONCAT(sTemp,',',sTempChd);
    SELECT GROUP_CONCAT(menu_id) INTO sTempChd FROM t_menu
    WHERE FIND_IN_SET(parent_id,sTempChd)>0;
END WHILE;
RETURN sTemp;
END
;;
delimiter ;

ALTER TABLE t_device ADD third_device INT(1) NOT NULL DEFAULT 0 COMMENT '标识是否为第三方设备(0否、1是)';
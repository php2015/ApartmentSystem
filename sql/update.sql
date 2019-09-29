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

INSERT  INTO `t_village`(`village_id`,`village_name`,`village_address`,`village_type`,`building_num`,`room_num`,`charge`,`charge_phone`,`jw`,`charge_pic`,`village_pic`,`village_orther`,`village_msg`,`create_by`,`create_time`,`update_by`,`update_time`,`del_flag`)
VALUES (null,'第三方对接小区',' ',1,200,2000,'admin','15359253632','12.32；30.21',NULL,'D://village.jpg','学校、医院、公园、地铁','小区很好','admin','2019-09-02 16:14:25',NULL,NULL,0);

INSERT  INTO `t_building`(`building_id`,`village_id`,`building_name`,`building_type`,`building_num`,`remark`,`create_by`,`create_time`,`update_by`,`update_time`,`del_flag`,`kz_room`,`yz_room`,`outTime_room`,`total_room`)
VALUES (null,5,'building021',0,0,'building021building021building021',NULL,NULL,NULL,NULL,0,0,0,0,0)
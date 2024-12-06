/*表中所需的触发器，常量*/
use demo_db;


/*对imp表删除，视为售出操作*/
SET @count_delete=0;
CREATE TRIGGER imp_delete
BEFORE DELETE ON lb_tabl_imp
FOR EACH ROW
begin
DECLARE delete_id BIGINT;
SET @count_delete=@count_delete+1;
SET delete_id=OLD.id;
INSERT INTO lb_tabl_deal 
(`Deal_order`,sort,`Product_name`,`Amount`,`Unite`,`Allcost`,`Deliver_order`,`Deal_date`)
SELECT @count_delete,sort,`Product_name`,`Amount`,`Unite`,`Allcost`,`Deliver_order`,CURDATE() FROM lb_tabl_imp imp WHERE imp.id=delete_id;
END;

CREATE TRIGGER deal_insert
AFTER INSERT ON lb_tabl_deal
FOR EACH ROW
begin
IF((SELECT COUNT(*) FROM lb_tabl_deal)=0)
THEN CALL set_auto_increment();
END if;
END;
DROP TRIGGER imp_delete;
DROP TRIGGER deal_insert;

/*所需的修改初始自增值*/
CREATE PROCEDURE set_auto_increment()
BEGIN
    SET @sql = 'ALTER TABLE lb_tabl_deal AUTO_INCREMENT = 1';
    PREPARE stmt FROM @sql;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END;
CALL set_auto_increment();
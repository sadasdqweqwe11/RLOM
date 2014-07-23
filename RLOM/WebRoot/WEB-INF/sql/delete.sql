DROP table IF EXISTS logistics_user;
DROP table IF EXISTS logistics_rlorder;
DROP table IF EXISTS logistics_rlorder_item;
DROP table IF EXISTS logistics_orderFile;
DROP table IF EXISTS logistics_file;
DROP table IF EXISTS logistics_trackingno;
DROP table IF EXISTS logistics_logistics;
DROP table IF EXISTS logistics_sku;
#delete from logistics_rlorder where id >= 11427;
#delete from logistics_sku;
#delete from logistics_logistics;


UPDATE logistics_rlorder_item SET skuid=REPLACE(skuid,'-','');
UPDATE logistics_rlorder SET skuno=REPLACE(skuno,'-','');
UPDATE logistics_sku SET skuno=REPLACE(skuno,' ','');

select * from logistics_rlorder where vendor is  null  group by date;

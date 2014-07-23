Create Database If Not Exists logistics Character Set UTF8;
use logistics;
#USER TABLE
create table If Not Exists logistics.logistics_user(
uid int(10) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
username	varchar(20),
nickname	varchar(20),
password	varchar(32),
secques	varchar(32),
spaceid	int(10) DEFAULT 0,
gender	int(10) DEFAULT 0,
adminid	int(10) DEFAULT 0,
groupid	int(5) DEFAULT 0,
groupexpiry	varchar(50),
extgroupids	char(60),
regip	char(15),
joindate	varchar(16),
lastip	char(15),
lastvisit	varchar(23),
lastactivity	varchar(23),
lastpost	varchar(23),
lastpostid	int(10) DEFAULT 0,
lastposttitle	varchar(60),
posts	int(10) DEFAULT 0,
digestposts	int(5) DEFAULT 0,
oltime	int(10) DEFAULT 0,
pageviews	int(10) DEFAULT 0,
credits	int(10) DEFAULT 0,
extcredits1	int(18) DEFAULT 0,
extcredits2	int(18) DEFAULT 0,
extcredits3	int(18) DEFAULT 0,
extcredits4	int(18) DEFAULT 0,
extcredits5	int(18) DEFAULT 0,
extcredits6	int(18) DEFAULT 0,
extcredits7	int(18) DEFAULT 0,
extcredits8	int(18) DEFAULT 0,
avatarshowid	int(10) DEFAULT 0,
email	varchar(50),
bday	varchar(10),
sigstatus	int(10) DEFAULT 0,
tpp	int(10) DEFAULT 0,
ppp	int(10) DEFAULT 0,
templateid	int(5) DEFAULT 0,
pmsound	int(10) DEFAULT 0,
showemail	int(10) DEFAULT 0,
invisible	int(10) DEFAULT 0,
newpm	int(10) DEFAULT 0,
newpmcount	int(10) DEFAULT 0,
accessmasks	int(10) DEFAULT 0,
onlinestate	int(10) DEFAULT 0,
newsletter	int(10) DEFAULT 0,
salt	varchar(10),
realname	varchar(10),
idcard	varchar(20)
);
insert into logistics_user(password,email) values ("123","com");

#ruilan order 锐蓝上传订单表
Create Table If Not Exists logistics.logistics_rlorder(
	id int(20) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	rlordernumber varchar(30),
	ordernumber varchar(30),
	vendor varchar(20),
	skuno varchar(30),
	itemname varchar(50),
	pinming varchar(50),
	quantity int(10),
	description varchar(255),
	buyername varchar(30),
	shipaddress1 varchar(100),
	shipaddress2 varchar(100),
	shipcity varchar(100),
	shipstate varchar(30),
	postalcode varchar(20),
	shipcountry varchar(30),
	buyerphonenumber varchar(20),
	date varchar(14),
	amount double,
	weight double,
	trackingno varchar(30),
	guojia varchar(30),
	marketplace varchar(20),
	account varchar(30),
	currency varchar(10),
	fileid int(20),
	logisticsid int(10),
	splitstatus int(4),
	uid int(10)
);


#alter table logistics_rlorder change ordernumber ordernumber varchar(50);
#alter table logistics_rlorder Add column splitstatus int(4) default 0 AFTER logisticsid;
#alter table logistics_rlorder Add column uid int(10) default 0 AFTER splitstatus;


alter table logistics_rlorder change date date varchar(14);
alter table logistics_rlorder Add column qua int(10) default 0 AFTER quantity;
alter table logistics_rlorder Add column weight double default 0 AFTER amount;
alter table logistics_rlorder Add column amou double default 0 AFTER amount;

update logistics_rlorder  set qua=quantity;
update logistics_rlorder  set amou=amount;

select * from logistics_rlorder where amou=0;

alter table logistics_rlorder drop column amount;
alter table logistics_rlorder drop column quantity;

alter table logistics_rlorder change amou amount double;
alter table logistics_rlorder change qua quantity int(10);

select * from logistics_rlorder ;



#order_item
Create Table If Not Exists logistics.logistics_rlorder_item(
	id int(20) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	rlorderid int(20),
	orderno varchar(50),
	itemno varchar(50),
	skuid varchar(40),
	quantity int(10),
	description varchar(255)
);

alter table logistics_rlorder_item Add column qua int(10) default 0 AFTER skuid;
update logistics_rlorder_item  set qua=quantity;
select * from logistics_rlorder_item;
alter table logistics_rlorder_item drop column quantity;
alter table logistics_rlorder_item change qua quantity int(10);





#order file 上传excel文件表
Create Table If Not Exists logistics.logistics_orderFile(
	id int(20) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	uid int(10),
	postdatetime date,
	filename varchar(100),
	description varchar(100),
	filetype varchar(10),
	filesize int(10),
	originalfilename varchar(255);
);

#alter table logistics_orderFile change  filename  filename varchar(100);

#file 生成文件表
Create Table If Not Exists logistics.logistics_file(
	id int(20) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	postdatetime date,
	filename varchar(100),
	description varchar(100),
	filetype varchar(10),
	filesize int(10),
	url varchar(100)
);

#trackingno
Create Table If Not Exists logistics.logistics_trackingno(
	id int(20) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	trackingno varchar(30),
	rlorderid int(20) DEFAULT 0,
	logisticsid int(10) DEFAULT 0,
	isused int(2) DEFAULT 0
);

#
Create Table If Not Exists logistics.logistics_logistics(
	id int(10) UNSIGNED PRIMARY KEY,
	name varchar(50),
	engname varchar(50),
	logifunc varchar(50),
	account varchar(50),
	password varchar(50),
	trackingstore int(5) default 0,
	mailto varchar(50),
	address varchar(200),
	postalcode varchar(20),
	phonenumber1 varchar(50),
	phonenumber2 varchar(50),
	remarks varchar(100)
);
insert into logistics_logistics(id,name,engname,logifunc,address,account,mailto,postalcode,phonenumber1,phonenumber2,remarks,trackingstore) values (999,"请手动选择物流","select","","","","","","","","",0);
insert into logistics_logistics(id,name,engname,logifunc,address,account,mailto,postalcode,phonenumber1,phonenumber2,remarks,trackingstore) values (1,"北京小包","BJXB","北京燕文物流有限公司","北京朝阳区万红路5号蓝涛中心A101","106567","张楠 转 106567","100016","13701311116","010-64621881/64656790","请拆包处理",1);
insert into logistics_logistics(id,name,engname,logifunc,address,account,mailto,postalcode,phonenumber1,phonenumber2,remarks,trackingstore) values (2,"北京小包-不挂号","BJXBBGH","北京燕文物流有限公司","北京朝阳区万红路5号蓝涛中心A101","106567","张楠 转 106567","100016","13701311116","010-64621881/64656790","请拆包处理",0);
insert into logistics_logistics(id,name,engname,logifunc,address,account,mailto,postalcode,phonenumber1,phonenumber2,remarks,trackingstore) values (3,"上海小包","SHXB","北京燕文物流有限公司","上海市闸北区灵石路(巨丰商务）697-3号7号楼101室","207711","张楠 转 207711","","021-36031631 / 36031637","021-36359765/66/67/68/69","请拆包处理",1);
insert into logistics_logistics(id,name,engname,logifunc,address,account,mailto,postalcode,phonenumber1,phonenumber2,remarks,trackingstore) values (4,"上海小包-不挂号","SHXBBGH","北京燕文物流有限公司","上海市闸北区灵石路(巨丰商务）697-3号7号楼101室","207711","张楠 转 207711","","021-36031631 / 36031637","021-36359765/66/67/68/69","请拆包处理",0);
insert into logistics_logistics(id,name,engname,logifunc,address,account,mailto,postalcode,phonenumber1,phonenumber2,remarks,trackingstore) values (5,"义乌上海小包 ","YWSHXB","北京燕文物流有限公司","上海市闸北区灵石路(巨丰商务）697-3号7号楼101室","207711","张楠 转 207711","","021-36031631 / 36031637","021-36359765/66/67/68/69","请拆包处理",1);
insert into logistics_logistics(id,name,engname,logifunc,address,account,mailto,postalcode,phonenumber1,phonenumber2,remarks,trackingstore) values (6,"义乌上海小包不挂号","YWSHXBBGH","北京燕文物流有限公司","上海市闸北区灵石路(巨丰商务）697-3号7号楼101室","207711","张楠 转 207711","","021-36031631 / 36031637","021-36359765/66/67/68/69","请拆包处理",0);
insert into logistics_logistics(id,name,engname,logifunc,address,account,mailto,postalcode,phonenumber1,phonenumber2,remarks,trackingstore) values (7,"深圳北京小包","SZBJXB","北京燕文物流有限公司","深圳市宝安区水库路111号星宏科技园D栋一层","303616","张楠 转 303616","","0755-83576869 83576658","0755-61190719/20/21/22","请拆包处理",1);
insert into logistics_logistics(id,name,engname,logifunc,address,account,mailto,postalcode,phonenumber1,phonenumber2,remarks,trackingstore) values (8,"深圳北京小包-不挂号","SZBJXBBGH","北京燕文物流有限公司","深圳市宝安区水库路111号星宏科技园D栋一层","303616","张楠 转 303616","","0755-83576869 83576658","0755-61190719/20/21/22","请拆包处理",0);
insert into logistics_logistics(id,name,engname,logifunc,address,account,mailto,postalcode,phonenumber1,phonenumber2,remarks,trackingstore) values (9,"深圳思比特","SZSBT","深圳思比特物流有限公司","深圳市福田区华强北南光大厦1204","XGRLKJ","赖仁誉","","15112287347","","香港锐蓝科技",9);
insert into logistics_logistics(id,name,engname,logifunc,address,account,mailto,postalcode,phonenumber1,phonenumber2,remarks,trackingstore) values (10,"北京EQUICK","BJEQUICK","网易速达国际货运代理有限公司","北京市朝阳区百子湾东里414-9,小姚（PEK130011)","PEK130011","王海红/小姚","","18018602811","010-87210757","客户号：PEK130011",2);
insert into logistics_logistics(id,name,engname,logifunc,address,account,mailto,postalcode,phonenumber1,phonenumber2,remarks,trackingstore) values (11,"深圳EQUICK","SZEQUICK","网易速达国际货运代理有限公司","深圳市南山区茶光路1063号一本电子商务产业园1楼118号","PEK130011","韩经理","","13923842301","","客户号：PEK130011",2);
insert into logistics_logistics(id,name,engname,logifunc,address,account,mailto,postalcode,phonenumber1,phonenumber2,remarks,trackingstore) values (12,"上海EQUICK","SHEQUICK","网易速达国际货运代理有限公司","上海市闵行区华翔路2001号院内网易速达","PEK130011","王海红","","021-34305258","","客户号：PEK130011",2);
insert into logistics_logistics(id,name,engname,logifunc,address,account,mailto,postalcode,phonenumber1,phonenumber2,remarks,trackingstore) values (13,"上海EUB","SHEUB","北京快达国际物流有限公司","上海市青浦区徐泾镇华徐公路719号C座212快达物流处理中心转C2616 ","C0002616","快达转C2616","","15811068059","","客户号：C0002616，请拆包处理",4);
insert into logistics_logistics(id,name,engname,logifunc,address,account,mailto,postalcode,phonenumber1,phonenumber2,remarks,trackingstore) values (14,"北京EUB","BJEUB","北京快达国际物流有限公司","北京市顺义区南法信镇北法信村西鹏程食品公司北侧2号院快达物流转C2995","C0002995","快达转C2995","","15811068059","","客户号：C0002995，请拆包处理",3);
insert into logistics_logistics(id,name,engname,logifunc,address,account,mailto,postalcode,phonenumber1,phonenumber2,remarks,trackingstore) values (15,"深圳荷兰小包","SZHLXB","北京燕文物流有限公司","深圳市宝安区水库路111号星宏科技园D栋一层","303616","张楠 转 303616","","0755-83576869 83576658","0755-61190719/20/21/22","请拆包处理",0);
insert into logistics_logistics(id,name,engname,logifunc,address,account,mailto,postalcode,phonenumber1,phonenumber2,remarks,trackingstore) values (16,"上海荷兰小包","SHHLXB","北京燕文物流有限公司","上海市闸北区灵石路(巨丰商务）697-3号7号楼101室","207711","张楠 转 207711","","021-36359765/66/67/68/69","021-36031631 / 36031637","请拆包处理",0);
insert into logistics_logistics(id,name,engname,logifunc,address,account,mailto,postalcode,phonenumber1,phonenumber2,remarks,trackingstore) values (17,"北京荷兰小包","BJHLXB","北京燕文物流有限公司","北京朝阳区万红路5号蓝涛中心A101","106567","张楠 转 106567","100016","13701311116","010-64621881/64656790","请拆包处理",0);
insert into logistics_logistics(id,name,engname,logifunc,address,account,mailto,postalcode,phonenumber1,phonenumber2,remarks,trackingstore) values (18,"义乌荷兰小包","YWHLXB","北京燕文物流有限公司","义乌市北苑秋实路103号","207711","张楠 转 207711","","0579-85238581","","请拆包处理",0);
insert into logistics_logistics(id,name,engname,logifunc,address,account,mailto,postalcode,phonenumber1,phonenumber2,remarks,trackingstore) values (19,"深圳燕邮宝","SZYYB","北京燕文物流有限公司","深圳市宝安区水库路111号星宏科技园D栋一层","303616","张楠 转 303616","","0755-83576869 83576658","0755-61190719/20/21/22","请拆包处理",0);
insert into logistics_logistics(id,name,engname,logifunc,address,account,mailto,postalcode,phonenumber1,phonenumber2,remarks,trackingstore) values (20,"上海燕邮宝","SHYYB","北京燕文物流有限公司","上海市闸北区灵石路(巨丰商务）697-3号7号楼101室","207711","张楠 转 207711","","021-36031631 / 36031637","021-36359765/66/67/68/69","请拆包处理",0);
insert into logistics_logistics(id,name,engname,logifunc,address,account,mailto,postalcode,phonenumber1,phonenumber2,remarks,trackingstore) values (21,"北京燕邮宝","BJYYB","北京燕文物流有限公司","北京朝阳区万红路5号蓝涛中心A101","106567","张楠 转 106567","100016","13701311116","010-64621881/64656790","请拆包处理",0);
insert into logistics_logistics(id,name,engname,logifunc,address,account,mailto,postalcode,phonenumber1,phonenumber2,remarks,trackingstore) values (22,"义乌燕邮宝","YWYYB","北京燕文物流有限公司","义乌市北苑秋实路103号","207711","张楠 转 207711","","0579-85238581","","请拆包处理",0);
insert into logistics_logistics(id,name,engname,logifunc,address,account,mailto,postalcode,phonenumber1,phonenumber2,remarks,trackingstore) values (23,"深圳YODEL英国小包","SZYODELXB","北京燕文物流有限公司","深圳市宝安区水库路111号星宏科技园D栋一层","303616","张楠 转 303616","","0755-83576869 83576658","0755-61190719/20/21/22","请拆包处理",0);
insert into logistics_logistics(id,name,engname,logifunc,address,account,mailto,postalcode,phonenumber1,phonenumber2,remarks,trackingstore) values (24,"上海YODEL英国小包","SHYODELXB","北京燕文物流有限公司","上海市闸北区灵石路(巨丰商务）697-3号7号楼101室","207711","张楠 转 207711","","021-36031631 / 36031637","021-36359765/66/67/68/69","请拆包处理",0);
insert into logistics_logistics(id,name,engname,logifunc,address,account,mailto,postalcode,phonenumber1,phonenumber2,remarks,trackingstore) values (25,"北京YODEL英国小包","BJYODELXB","北京燕文物流有限公司","北京朝阳区万红路5号蓝涛中心A101","106567","张楠 转 106567","100016","13701311116","010-64621881/64656790","请拆包处理",0);
insert into logistics_logistics(id,name,engname,logifunc,address,account,mailto,postalcode,phonenumber1,phonenumber2,remarks,trackingstore) values (26,"义乌-上海YODEL英国小包","YWSHYODELXB","北京燕文物流有限公司","义乌市北苑秋实路103号","207711","张楠 转 207711","","0579-85238581","","请拆包处理",0);
insert into logistics_logistics(id,name,engname,logifunc,address,account,mailto,postalcode,phonenumber1,phonenumber2,remarks,trackingstore) values (27,"深圳YODEL英国小包（带电）","SZYODELDD","北京燕文物流有限公司","深圳市宝安区水库路111号星宏科技园D栋一层","303616","张楠 转 303616","","0755-83576869 83576658","0755-61190719/20/21/22","请拆包处理",0);
insert into logistics_logistics(id,name,engname,logifunc,address,account,mailto,postalcode,phonenumber1,phonenumber2,remarks,trackingstore) values (28,"上海YODEL英国小包（带电）","HSYODELDD","北京燕文物流有限公司","上海市闸北区灵石路(巨丰商务）697-3号7号楼101室","207711","张楠 转 207711","","021-36031631 / 36031637","021-36359765/66/67/68/69","请拆包处理",0);
insert into logistics_logistics(id,name,engname,logifunc,address,account,mailto,postalcode,phonenumber1,phonenumber2,remarks,trackingstore) values (29,"北京YODEL英国小包（带电）","BJYODELDD","北京燕文物流有限公司","北京朝阳区万红路5号蓝涛中心A101","106567","张楠 转 106567","100016","13701311116","010-64621881/64656790","请拆包处理",0);
insert into logistics_logistics(id,name,engname,logifunc,address,account,mailto,postalcode,phonenumber1,phonenumber2,remarks,trackingstore) values (30,"义乌-上海YODEL英国小包（带电）","YWSHYODELDD","北京燕文物流有限公司","义乌市北苑秋实路103号","207711","张楠 转 207711","","0579-85238581","","请拆包处理",0);
insert into logistics_logistics(id,name,engname,logifunc,address,account,mailto,postalcode,phonenumber1,phonenumber2,remarks,trackingstore) values (31,"深圳YODEL标准","SZYODEL","北京燕文物流有限公司","深圳市宝安区水库路111号星宏科技园D栋一层","303616","张楠 转 303616","","0755-83576869 83576658","0755-61190719/20/21/22","请拆包处理",0);
insert into logistics_logistics(id,name,engname,logifunc,address,account,mailto,postalcode,phonenumber1,phonenumber2,remarks,trackingstore) values (32,"上海YODEL标准","SHYODEL","北京燕文物流有限公司","上海市闸北区灵石路(巨丰商务）697-3号7号楼101室","207711","张楠 转 207711","","021-36031631 / 36031637","021-36359765/66/67/68/69","请拆包处理",0);
insert into logistics_logistics(id,name,engname,logifunc,address,account,mailto,postalcode,phonenumber1,phonenumber2,remarks,trackingstore) values (33,"北京YODEL标准","BJYODEL","北京燕文物流有限公司","北京朝阳区万红路5号蓝涛中心A101","106567","张楠 转 106567","100016","13701311116","010-64621881/64656790","请拆包处理",0);
insert into logistics_logistics(id,name,engname,logifunc,address,account,mailto,postalcode,phonenumber1,phonenumber2,remarks,trackingstore) values (34,"义乌-上海YODEL标准","YWYODEL","北京燕文物流有限公司","义乌市北苑秋实路103号","207711","张楠 转 207711","","0579-85238581","","请拆包处理",0);
insert into logistics_logistics(id,name,engname,logifunc,address,account,mailto,postalcode,phonenumber1,phonenumber2,remarks,trackingstore) values (35,"燕文上海EUB","YWSHEUB","北京燕文物流有限公司","上海市闸北区灵石路(巨丰商务）697-3号7号楼101室","207711","张楠 转 207711","","021-36031631 / 36031637","021-36359765/66/67/68/69","请拆包处理",0);
insert into logistics_logistics(id,name,engname,logifunc,address,account,mailto,postalcode,phonenumber1,phonenumber2,remarks,trackingstore) values (36,"深圳燕邮宝-不挂号","SZYYBBGH","北京燕文物流有限公司","深圳市宝安区水库路111号星宏科技园D栋一层","303616","张楠 转 303616","","0755-83576869 83576658","0755-61190719/20/21/22","请拆包处理",0);
insert into logistics_logistics(id,name,engname,logifunc,address,account,mailto,postalcode,phonenumber1,phonenumber2,remarks,trackingstore) values (37,"上海燕邮宝-不挂号","SHYYBBGH","北京燕文物流有限公司","上海市闸北区灵石路(巨丰商务）697-3号7号楼101室","207711","张楠 转 207711","","021-36031631 / 36031637","021-36359765/66/67/68/69","请拆包处理",0);
insert into logistics_logistics(id,name,engname,logifunc,address,account,mailto,postalcode,phonenumber1,phonenumber2,remarks,trackingstore) values (38,"北京燕邮宝-不挂号","BJYYBBGH","北京燕文物流有限公司","北京朝阳区万红路5号蓝涛中心A101","106567","张楠 转 106567","100016","13701311116","010-64621881/64656790","请拆包处理",0);
insert into logistics_logistics(id,name,engname,logifunc,address,account,mailto,postalcode,phonenumber1,phonenumber2,remarks,trackingstore) values (39,"义乌燕邮宝-不挂号","YWYYBBGH","北京燕文物流有限公司","义乌市北苑秋实路103号","207711","张楠 转 207711","","0579-85238581","","请拆包处理",0);



#drop table logistics.logistics_sku;
Create Table If Not Exists logistics.logistics_sku(
	skuno varchar(40) PRIMARY KEY,
	skucode varchar(40),
	des varchar(200),
	vendor varchar(20),
	area int(3) DEFAULT 0,
	weight double,
	price double,
	name varchar(50),
	pinming varchar(50),
	battery int(3) DEFAULT 0,
	promotion int(3) DEFAULT 0,
	liquid int(3) DEFAULT 0,
	other int(3) DEFAULT 0,
	UNIQUE (skuno)
);
#alter table logistics_sku drop column id;
#alter table logistics_sku change skuno skuno varchar(40) primary key;
#alter table logistics_sku Add column name varchar(50) default "" AFTER price; 
#alter table logistics_sku Add column pinming varchar(50) default "" AFTER name;

#alter table logistics_sku Add column des varchar(200) default "" AFTER skuno;
#alter table logistics_sku Add column skucode varchar(40) default "" AFTER skuno;  

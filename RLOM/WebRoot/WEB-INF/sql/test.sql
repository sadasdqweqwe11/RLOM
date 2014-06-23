update logistics_trackingno set isused=0  where trackingno="1";

select * from logistics_rlorder;
select * from logistics_rlorder_item where id=11048;
select * from logistics_orderFile;
select * from logistics_trackingno;
select * from logistics_logistics where id=0;

select * from logistics_rlorder where date>="20140512" and date <="20140516" and logisticsid = 3;

update logistics_sku set area=1  where skuno="PW305";



delete from logistics_trackingno where logisticsid = 2;


select * from bbs_article where tid=20 and posterid="dong";
delete from bbs_article where aid=1;
select * from bbs_user;
select * from bbs_boardfield;
select * from bbs_board board order by board.topics desc;
update bbs_boardfield set moderators="maya,dong" where bid="talking"
select * from bbs_topic topic where topic.bid='notice' order by postdatetime desc;

select * from bbs_article where tid=14 order by Postdatetime desc

update bbs_boardfield set Postperm="1" where bid="notice";
update bbs_user set groupid=1 where uid="maya";
select *  from bbs_publishtime;
select *  from bbs_userfield;
select * from bbs_myarticle;
delete from bbs_myarticle;

select * from bbs_mytopic;
select * from bbs_user;
select * from bbs_board;
select * from bbs_hottopic;

delete from bbs_topic;
delete  from bbs_mytopic;
delete  from bbs_myarticle;
delete  from bbs_article;
delete  from bbs_publishtime;
delete  from bbs_hottopic;

delete  from bbs_board;
delete  from bbs_boardfield;

SELECT * FROM `bbs_user` AS t1 
JOIN (SELECT ROUND(RAND() * ((SELECT MAX(uid) FROM `bbs_user`)-(SELECT MIN(uid) FROM `bbs_user`))+(SELECT MIN(uid) FROM `bbs_user`)) AS id) AS t2 
WHERE t1.uid >= t2.id 
ORDER BY t1.uid LIMIT 1; 

select * from bbs_loginCookie;
delete from bbs_loginCookie;




update logistics_sku set area=1  where skuno="PW305";
update logistics_sku set area=3  where skuno="PR3050";
update logistics_sku set BATTERY=1  where skuno="PL403";
update logistics_sku set BATTERY=1  where skuno="PR3011";
update logistics_sku set BATTERY=1  where skuno="PH8802";
update logistics_sku set BATTERY=1  where skuno="PS1080";

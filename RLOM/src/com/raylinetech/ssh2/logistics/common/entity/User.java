package com.raylinetech.ssh2.logistics.common.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "logistics_user")
public class User implements Serializable {
	/**
	 * SERIAL CODE
	 */
	private static final long serialVersionUID = -8579475658005111814L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long uid;
	@Column
	private String username;
	@Column
	private String nickname;
	@Column
	private String password;
	@Column
	private String secques;
	@Column
	private int spaceid;
	@Column
	private int gender;
	@Column
	private int adminid;
	@Column
	private int groupid;
	@Column
	private String realname;
	@Column
	private String idcard;
	@Column
	private String groupexpiry;
	@Column
	private String extgroupids;
	@Column
	private String regip;
	@Column
	private String joindate;
	@Column
	private String lastip;
	@Column
	private String lastvisit;
	@Column
	private String lastactivity;
	@Column
	private String lastpost;
	@Column
	private int lastpostid;
	@Column
	private String lastposttitle;
	@Column
	private int posts;
	@Column
	private int digestposts;
	@Column
	private int oltime;
	@Column
	private int pageviews;
	@Column
	private int credits;
	@Column
	private int extcredits1;
	@Column
	private int extcredits2;
	@Column
	private int extcredits3;
	@Column
	private int extcredits4;
	@Column
	private int extcredits5;
	@Column
	private int extcredits6;
	@Column
	private int extcredits7;
	@Column
	private int extcredits8;
	@Column
	private int avatarshowid;
	@Column
	private String email;
	@Column
	private String bday;
	@Column
	private int sigstatus;
	@Column
	private int tpp;
	@Column
	private int ppp;
	@Column
	private int templateid;
	@Column
	private int pmsound;
	@Column
	private int showemail;
	@Column
	private int invisible;
	@Column
	private int newpm;
	@Column
	private int newpmcount;
	@Column
	private int accessmasks;
	@Column
	private int onlinestate;
	@Column
	private int newsletter;
	@Column
	private String salt;
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSecques() {
		return secques;
	}
	public void setSecques(String secques) {
		this.secques = secques;
	}
	public int getSpaceid() {
		return spaceid;
	}
	public void setSpaceid(int spaceid) {
		this.spaceid = spaceid;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getAdminid() {
		return adminid;
	}
	public void setAdminid(int adminid) {
		this.adminid = adminid;
	}
	public int getGroupid() {
		return groupid;
	}
	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getGroupexpiry() {
		return groupexpiry;
	}
	public void setGroupexpiry(String groupexpiry) {
		this.groupexpiry = groupexpiry;
	}
	public String getExtgroupids() {
		return extgroupids;
	}
	public void setExtgroupids(String extgroupids) {
		this.extgroupids = extgroupids;
	}
	public String getRegip() {
		return regip;
	}
	public void setRegip(String regip) {
		this.regip = regip;
	}
	public String getJoindate() {
		return joindate;
	}
	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}
	public String getLastip() {
		return lastip;
	}
	public void setLastip(String lastip) {
		this.lastip = lastip;
	}
	public String getLastvisit() {
		return lastvisit;
	}
	public void setLastvisit(String lastvisit) {
		this.lastvisit = lastvisit;
	}
	public String getLastactivity() {
		return lastactivity;
	}
	public void setLastactivity(String lastactivity) {
		this.lastactivity = lastactivity;
	}
	public String getLastpost() {
		return lastpost;
	}
	public void setLastpost(String lastpost) {
		this.lastpost = lastpost;
	}
	public int getLastpostid() {
		return lastpostid;
	}
	public void setLastpostid(int lastpostid) {
		this.lastpostid = lastpostid;
	}
	public String getLastposttitle() {
		return lastposttitle;
	}
	public void setLastposttitle(String lastposttitle) {
		this.lastposttitle = lastposttitle;
	}
	public int getPosts() {
		return posts;
	}
	public void setPosts(int posts) {
		this.posts = posts;
	}
	public int getDigestposts() {
		return digestposts;
	}
	public void setDigestposts(int digestposts) {
		this.digestposts = digestposts;
	}
	public int getOltime() {
		return oltime;
	}
	public void setOltime(int oltime) {
		this.oltime = oltime;
	}
	public int getPageviews() {
		return pageviews;
	}
	public void setPageviews(int pageviews) {
		this.pageviews = pageviews;
	}
	public int getCredits() {
		return credits;
	}
	public void setCredits(int credits) {
		this.credits = credits;
	}
	public int getExtcredits1() {
		return extcredits1;
	}
	public void setExtcredits1(int extcredits1) {
		this.extcredits1 = extcredits1;
	}
	public int getExtcredits2() {
		return extcredits2;
	}
	public void setExtcredits2(int extcredits2) {
		this.extcredits2 = extcredits2;
	}
	public int getExtcredits3() {
		return extcredits3;
	}
	public void setExtcredits3(int extcredits3) {
		this.extcredits3 = extcredits3;
	}
	public int getExtcredits4() {
		return extcredits4;
	}
	public void setExtcredits4(int extcredits4) {
		this.extcredits4 = extcredits4;
	}
	public int getExtcredits5() {
		return extcredits5;
	}
	public void setExtcredits5(int extcredits5) {
		this.extcredits5 = extcredits5;
	}
	public int getExtcredits6() {
		return extcredits6;
	}
	public void setExtcredits6(int extcredits6) {
		this.extcredits6 = extcredits6;
	}
	public int getExtcredits7() {
		return extcredits7;
	}
	public void setExtcredits7(int extcredits7) {
		this.extcredits7 = extcredits7;
	}
	public int getExtcredits8() {
		return extcredits8;
	}
	public void setExtcredits8(int extcredits8) {
		this.extcredits8 = extcredits8;
	}
	public int getAvatarshowid() {
		return avatarshowid;
	}
	public void setAvatarshowid(int avatarshowid) {
		this.avatarshowid = avatarshowid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBday() {
		return bday;
	}
	public void setBday(String bday) {
		this.bday = bday;
	}
	public int getSigstatus() {
		return sigstatus;
	}
	public void setSigstatus(int sigstatus) {
		this.sigstatus = sigstatus;
	}
	public int getTpp() {
		return tpp;
	}
	public void setTpp(int tpp) {
		this.tpp = tpp;
	}
	public int getPpp() {
		return ppp;
	}
	public void setPpp(int ppp) {
		this.ppp = ppp;
	}
	public int getTemplateid() {
		return templateid;
	}
	public void setTemplateid(int templateid) {
		this.templateid = templateid;
	}
	public int getPmsound() {
		return pmsound;
	}
	public void setPmsound(int pmsound) {
		this.pmsound = pmsound;
	}
	public int getShowemail() {
		return showemail;
	}
	public void setShowemail(int showemail) {
		this.showemail = showemail;
	}
	public int getInvisible() {
		return invisible;
	}
	public void setInvisible(int invisible) {
		this.invisible = invisible;
	}
	public int getNewpm() {
		return newpm;
	}
	public void setNewpm(int newpm) {
		this.newpm = newpm;
	}
	public int getNewpmcount() {
		return newpmcount;
	}
	public void setNewpmcount(int newpmcount) {
		this.newpmcount = newpmcount;
	}
	public int getAccessmasks() {
		return accessmasks;
	}
	public void setAccessmasks(int accessmasks) {
		this.accessmasks = accessmasks;
	}
	public int getOnlinestate() {
		return onlinestate;
	}
	public void setOnlinestate(int onlinestate) {
		this.onlinestate = onlinestate;
	}
	public int getNewsletter() {
		return newsletter;
	}
	public void setNewsletter(int newsletter) {
		this.newsletter = newsletter;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public User(long uid, String username, String nickname, String password,
			String secques, int spaceid, int gender, int adminid, int groupid,
			String realname, String idcard, String groupexpiry,
			String extgroupids, String regip, String joindate, String lastip,
			String lastvisit, String lastactivity, String lastpost,
			int lastpostid, String lastposttitle, int posts, int digestposts,
			int oltime, int pageviews, int credits, int extcredits1,
			int extcredits2, int extcredits3, int extcredits4, int extcredits5,
			int extcredits6, int extcredits7, int extcredits8,
			int avatarshowid, String email, String bday, int sigstatus,
			int tpp, int ppp, int templateid, int pmsound, int showemail,
			int invisible, int newpm, int newpmcount, int accessmasks,
			int onlinestate, int newsletter, String salt) {
		super();
		this.uid = uid;
		this.username = username;
		this.nickname = nickname;
		this.password = password;
		this.secques = secques;
		this.spaceid = spaceid;
		this.gender = gender;
		this.adminid = adminid;
		this.groupid = groupid;
		this.realname = realname;
		this.idcard = idcard;
		this.groupexpiry = groupexpiry;
		this.extgroupids = extgroupids;
		this.regip = regip;
		this.joindate = joindate;
		this.lastip = lastip;
		this.lastvisit = lastvisit;
		this.lastactivity = lastactivity;
		this.lastpost = lastpost;
		this.lastpostid = lastpostid;
		this.lastposttitle = lastposttitle;
		this.posts = posts;
		this.digestposts = digestposts;
		this.oltime = oltime;
		this.pageviews = pageviews;
		this.credits = credits;
		this.extcredits1 = extcredits1;
		this.extcredits2 = extcredits2;
		this.extcredits3 = extcredits3;
		this.extcredits4 = extcredits4;
		this.extcredits5 = extcredits5;
		this.extcredits6 = extcredits6;
		this.extcredits7 = extcredits7;
		this.extcredits8 = extcredits8;
		this.avatarshowid = avatarshowid;
		this.email = email;
		this.bday = bday;
		this.sigstatus = sigstatus;
		this.tpp = tpp;
		this.ppp = ppp;
		this.templateid = templateid;
		this.pmsound = pmsound;
		this.showemail = showemail;
		this.invisible = invisible;
		this.newpm = newpm;
		this.newpmcount = newpmcount;
		this.accessmasks = accessmasks;
		this.onlinestate = onlinestate;
		this.newsletter = newsletter;
		this.salt = salt;
	}
	public User() {
		super();
	}

}

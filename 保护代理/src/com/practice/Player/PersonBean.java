package com.practice.Player;
/*
 * 这个接口可以设置和取得人的名字、性别、常用英雄和赞数量
 */
public interface PersonBean {
	String getPlayerID();
	String getGender();
	String getCommonHero();
	int getYesNumber();
	
	void setPlayerID(String PlayerID);
	void setGender(String gender);
	void setCommonHero(String CommonHero);
	void setYesNumber();
	void setAllCount(int Count);
}

package com.practice.Player;
/*
 * ����ӿڿ������ú�ȡ���˵����֡��Ա𡢳���Ӣ�ۺ�������
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

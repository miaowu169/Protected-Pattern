package com.practice.Person.Impl;

import com.practice.Player.PersonBean;

public class PersonBeanImpl implements PersonBean {
	String playerID;
	String gender;
	String CommonHero;
	int yesAllCount;

	public String getPlayerID() {
		return playerID;
	}

	public String getGender() {
		return gender;
	}

	public String getCommonHero() {
		return CommonHero;
	}

	public int getYesNumber() {
		return yesAllCount;
	}

	public void setPlayerID(String playerID) {
		this.playerID = playerID;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setCommonHero(String CommonHero) {
		this.CommonHero = CommonHero;
	}

	public void setYesNumber() {
		yesAllCount++;
	}

	public void setAllCount(int Count) {
		this.yesAllCount = Count;
	}
}

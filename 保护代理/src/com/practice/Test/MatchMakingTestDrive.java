package com.practice.Test;
import java.lang.reflect.*;
import java.util.*;

import com.practice.Person.Impl.PersonBeanImpl;
import com.practice.Player.PersonBean;
import com.practice.Proxy.NonOwnerInvocationHandler;
import com.practice.Proxy.OwnerInvocationHandler;
 
public class MatchMakingTestDrive {
    // 实例变量, 当作是保存顾客的“数据库”
	Hashtable<String, PersonBean> datingDB = new Hashtable<String, PersonBean>();
 	
	public static void main(String[] args) {
		MatchMakingTestDrive test = new MatchMakingTestDrive();
		test.drive();
	}
 
	public MatchMakingTestDrive() {
        // 在构造器中初始化数据库
		initializeDatabase();
	}
 
	public void drive() {
		PersonBean MW = getPersonFromDatabase("妙乌");  //从数据库中取出一个人
		PersonBean ownerProxy = getOwnerProxy(MW); // 创建这个人的拥有者代理
		System.out.println("账号拥有者的游戏ID " + ownerProxy.getPlayerID()); //  输出这个人的名字
		ownerProxy.setCommonHero("王昭君");  // 使用拥有者代理来设置自己的兴趣
		System.out.println("账号拥有者设置游戏常用英雄");
		System.out.println("账号拥有者常用的英雄:"+ownerProxy.getCommonHero());
		try {
            // 尝试用拥有者代理来给自己评分
			ownerProxy.setYesNumber();
		} catch (Exception e) {
            // 如果给自己评分会出错
			System.out.println("账号拥有者不能够给自己点赞");
		}
		System.out.println("账号总赞数为： " + ownerProxy.getYesNumber());
		
		System.out.println();
 
        // 创建一个非拥有者的代理
		PersonBean nonOwnerProxy = getNonOwnerProxy(MW);
		System.out.println("账号拥有者的游戏ID " + nonOwnerProxy.getPlayerID());
		try {
            // 尝试用非拥有者代理来设置兴趣
			nonOwnerProxy.setCommonHero("妲己");
		} catch (Exception e) {
            // 不可以给别人设置兴趣
			System.out.println("非拥有者不能设置别人的常用英雄");
		}
        // 可以给别人评分
		nonOwnerProxy.setYesNumber();
		// 查看信息
		System.out.println("非拥有者玩家查看账号所有者信息:");
		System.out.println("昵称:" + nonOwnerProxy.getPlayerID() + "\t性别:" + nonOwnerProxy.getGender()
				+ "\t常用英雄:" + nonOwnerProxy.getCommonHero());
		System.out.println("非拥有者玩家点赞");
		System.out.println("总赞数为： " + nonOwnerProxy.getYesNumber());
	}
 
    // 此方法需要一个person对象作为参数，然后返回该对象的代理
    // 因为代理和主题有相同的接口，所以我们返回接口PersonBean
	PersonBean getOwnerProxy(PersonBean person) {
        // 我们利用Proxy类的静态newProxyInstance方法创建代理对象(Java反射机制)
        return (PersonBean) Proxy.newProxyInstance( 
            	person.getClass().getClassLoader(),  // 将personBean的类载入器当作参数
            	person.getClass().getInterfaces(),   // 代理需要实现的接口
                new OwnerInvocationHandler(person)); // 调用非拥有者的处理器
	}
 
	PersonBean getNonOwnerProxy(PersonBean person) {
		
        return (PersonBean) Proxy.newProxyInstance(
            	person.getClass().getClassLoader(),   
            	person.getClass().getInterfaces(),   
                new NonOwnerInvocationHandler(person));
	}
 
	PersonBean getPersonFromDatabase(String name) {
		return (PersonBean)datingDB.get(name);
	}
 
    // 初始化“数据库”
	void initializeDatabase() {
		PersonBean MW = new PersonBeanImpl();
		MW.setPlayerID("妙乌");
		MW.setGender("男");
		MW.setCommonHero("狄仁杰");
		MW.setAllCount(7);
		datingDB.put(MW.getPlayerID(), MW);
 
		PersonBean ZX = new PersonBeanImpl();
		ZX.setPlayerID("Kelly Klosure");
		ZX.setCommonHero("ebay, movies, music");
		ZX.setYesNumber();
		datingDB.put(ZX.getPlayerID(), ZX);
	}
}


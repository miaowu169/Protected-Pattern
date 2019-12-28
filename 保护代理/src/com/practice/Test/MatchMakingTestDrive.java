package com.practice.Test;
import java.lang.reflect.*;
import java.util.*;

import com.practice.Person.Impl.PersonBeanImpl;
import com.practice.Player.PersonBean;
import com.practice.Proxy.NonOwnerInvocationHandler;
import com.practice.Proxy.OwnerInvocationHandler;
 
public class MatchMakingTestDrive {
    // ʵ������, �����Ǳ���˿͵ġ����ݿ⡱
	Hashtable<String, PersonBean> datingDB = new Hashtable<String, PersonBean>();
 	
	public static void main(String[] args) {
		MatchMakingTestDrive test = new MatchMakingTestDrive();
		test.drive();
	}
 
	public MatchMakingTestDrive() {
        // �ڹ������г�ʼ�����ݿ�
		initializeDatabase();
	}
 
	public void drive() {
		PersonBean MW = getPersonFromDatabase("����");  //�����ݿ���ȡ��һ����
		PersonBean ownerProxy = getOwnerProxy(MW); // ��������˵�ӵ���ߴ���
		System.out.println("�˺�ӵ���ߵ���ϷID " + ownerProxy.getPlayerID()); //  �������˵�����
		ownerProxy.setCommonHero("���Ѿ�");  // ʹ��ӵ���ߴ����������Լ�����Ȥ
		System.out.println("�˺�ӵ����������Ϸ����Ӣ��");
		System.out.println("�˺�ӵ���߳��õ�Ӣ��:"+ownerProxy.getCommonHero());
		try {
            // ������ӵ���ߴ��������Լ�����
			ownerProxy.setYesNumber();
		} catch (Exception e) {
            // ������Լ����ֻ����
			System.out.println("�˺�ӵ���߲��ܹ����Լ�����");
		}
		System.out.println("�˺�������Ϊ�� " + ownerProxy.getYesNumber());
		
		System.out.println();
 
        // ����һ����ӵ���ߵĴ���
		PersonBean nonOwnerProxy = getNonOwnerProxy(MW);
		System.out.println("�˺�ӵ���ߵ���ϷID " + nonOwnerProxy.getPlayerID());
		try {
            // �����÷�ӵ���ߴ�����������Ȥ
			nonOwnerProxy.setCommonHero("槼�");
		} catch (Exception e) {
            // �����Ը�����������Ȥ
			System.out.println("��ӵ���߲������ñ��˵ĳ���Ӣ��");
		}
        // ���Ը���������
		nonOwnerProxy.setYesNumber();
		// �鿴��Ϣ
		System.out.println("��ӵ������Ҳ鿴�˺���������Ϣ:");
		System.out.println("�ǳ�:" + nonOwnerProxy.getPlayerID() + "\t�Ա�:" + nonOwnerProxy.getGender()
				+ "\t����Ӣ��:" + nonOwnerProxy.getCommonHero());
		System.out.println("��ӵ������ҵ���");
		System.out.println("������Ϊ�� " + nonOwnerProxy.getYesNumber());
	}
 
    // �˷�����Ҫһ��person������Ϊ������Ȼ�󷵻ظö���Ĵ���
    // ��Ϊ�������������ͬ�Ľӿڣ��������Ƿ��ؽӿ�PersonBean
	PersonBean getOwnerProxy(PersonBean person) {
        // ��������Proxy��ľ�̬newProxyInstance���������������(Java�������)
        return (PersonBean) Proxy.newProxyInstance( 
            	person.getClass().getClassLoader(),  // ��personBean������������������
            	person.getClass().getInterfaces(),   // ������Ҫʵ�ֵĽӿ�
                new OwnerInvocationHandler(person)); // ���÷�ӵ���ߵĴ�����
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
 
    // ��ʼ�������ݿ⡱
	void initializeDatabase() {
		PersonBean MW = new PersonBeanImpl();
		MW.setPlayerID("����");
		MW.setGender("��");
		MW.setCommonHero("���ʽ�");
		MW.setAllCount(7);
		datingDB.put(MW.getPlayerID(), MW);
 
		PersonBean ZX = new PersonBeanImpl();
		ZX.setPlayerID("Kelly Klosure");
		ZX.setCommonHero("ebay, movies, music");
		ZX.setYesNumber();
		datingDB.put(ZX.getPlayerID(), ZX);
	}
}


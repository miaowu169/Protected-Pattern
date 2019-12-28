package com.practice.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.practice.Player.PersonBean;

public class NonOwnerInvocationHandler implements InvocationHandler {
	PersonBean person;
	
	public NonOwnerInvocationHandler(PersonBean person) {
		this.person = person;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException{
		try {
			if(method.getName().startsWith("get")) {
				return method.invoke(person, args);
			}else if(method.getName().equals("setYesNumber")){
				return method.invoke(person, args);
			}else if(method.getName().startsWith("set")) {
				throw new IllegalAccessException();
			}
		}catch(InvocationTargetException e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}

}

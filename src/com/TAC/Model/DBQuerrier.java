package com.TAC.Model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class DBQuerrier {
	static SessionFactory sessionFactory;
	static Configuration configuration;

	private static Session sessionStart() {
		if (sessionFactory == null) {
			configuration = new Configuration().configure();
			StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			sessionFactory = configuration
					.buildSessionFactory(standardServiceRegistry);
		}
		return (Session) sessionFactory.openSession();
	}
	
	public static String getDeviceList(String type) {
		return "1 "+type;
	}
	
	public static String getRecordList(){
		return "2 history";
	}
	
	public static String getDevice(String itemId){
		return "3 item";
	}
	
	public static String getRecord(String recordId){
		return "4 rec";
	}
	
	public static String borrowItem(String command){
		return "5 "+command;
	}
	
	public static String returnItem(String command){
		return "6 "+command;
	}
	
	public static String adminLogin(String password){
		return "7 "+password;
	}
	
	public static String getDeviceListAsAdmin(String type){
		return "8 "+type+" as admin";
	}
	
	public static String editLeftNumber(String command){
		return "9 "+command;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}

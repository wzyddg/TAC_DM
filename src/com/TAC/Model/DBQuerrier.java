package com.TAC.Model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SharedSessionContract;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

//+","

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
		Session session = sessionStart();
		String hql = "from Iteminfo where type = ? ";
		Query query = ((SharedSessionContract) session).createQuery(hql);
		query.setString(0, type);
		String result = "[";
		List<Iteminfo> infos = query.list();
		// if (infos.size() == 0)
		// return null;
		// System.out.println(users.get(0).getEmail());

		for (int i = 0; i < infos.size(); i++) {
			Iteminfo iteminfo = infos.get(i);
			if (iteminfo.leftcount > 0) {
				result = result + iteminfo.id + "," + iteminfo.name + ","
						+ iteminfo.description + "," + iteminfo.type + ","
						+ iteminfo.count + "," + iteminfo.leftcount;
				if (i != infos.size() - 1)
					result = result + "|";
			}
		}
		result += "]";
		return result;
	}

	public static String getRecordList() {
		Session session = sessionStart();
		String hql = "from Borrowrecord";
		Query query = ((SharedSessionContract) session).createQuery(hql);
		String result = "[";

		List<Borrowrecord> records = query.list();
		// System.out.println(records.size());

		for (int i = 0; i < records.size(); i++) {
			Borrowrecord record = records.get(i);
			result = result
					+ record.recordId
					+ ","
					+ record.borrowerName
					+ ","
					+ record.tele
					+ ","
					+ (record.itemId == null || record.itemId == 0 ? ""
							: record.itemId)
					+ ","
					+ (record.itemName == null ? "" : record.itemName)
					+ ","
					+ (record.itemInfo == null ? "" : record.itemInfo)
					+ ","
					+ record.borrowDate.getTime()
					+ ","
					+ (record.returnDate == null ? 0 : record.returnDate
							.getTime()) + "," + record.number;
			if (i < records.size() - 1) {
				result += "|";
			}
		}

		result += "]";
		return result;
	}

	public static String getDevice(String itemId) {
		return "3 item " + itemId;
	}

	public static String getRecord(String recordId) {
		return "4 rec " + recordId;
	}

	public static String borrowItem(String command) {
		return "5 borrow " + command;
	}

	public static String returnItem(String command) {
		return "6 return " + command;
	}

	public static String adminLogin(String password) {
		return "7 login " + password;
	}

	public static String getDeviceListAsAdmin(String type) {
		// return "8 admin "+type;
		Session session = sessionStart();
		String hql = "from Iteminfo where type = ? ";
		Query query = ((SharedSessionContract) session).createQuery(hql);
		String result = "[";

		query.setString(0, type);
		List<Iteminfo> infos = query.list();
		// if (infos.size() == 0)
		// return null;
		// System.out.println(users.get(0).getEmail());

		for (int i = 0; i < infos.size(); i++) {
			Iteminfo iteminfo = infos.get(i);
			result = result + iteminfo.id + "," + iteminfo.name + ","
					+ iteminfo.description + "," + iteminfo.type + ","
					+ iteminfo.count + "," + iteminfo.leftcount;
			if (i != infos.size() - 1)
				result = result + "|";

		}
		result += "]";
		return result;
	}

	public static String editLeftNumber(String command) {
		Session session = sessionStart();
		Transaction tx = session.beginTransaction();
		String hql = "update Iteminfo ii set ii.leftcount = ? where ii.id = ?";
		Query query = ((SharedSessionContract) session).createQuery(hql);

		String[] coms = command.split(",");

		int itemId = 0;
		int itemCount = -1;

		try {
			itemId = Integer.parseInt(coms[0]);
			itemCount = Integer.parseInt(coms[1]);
		} catch (Exception e) {
			// TODO: handle exception
			return "[0]";
		}

		System.out.println(command + " " + itemId + " " + itemCount);

		query.setInteger(0,itemCount);
		query.setInteger(1,itemId);
		
		int resInt = query.executeUpdate();
		tx.commit();
		session.close();

		return "["+resInt+"]";
	}

	public static String wrongCode(String command) {
		return "[]";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String result = "";
		// result=DBQuerrier.getDeviceList("ios");
		result = DBQuerrier.editLeftNumber("1,1");
		System.out.println(result);
	}

}

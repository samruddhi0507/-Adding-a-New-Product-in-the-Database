package com.utility;

import com.mysql.cj.Session;
import com.mysql.cj.xdevapi.SessionFactory;

import net.sf.ehcache.config.CacheConfiguration;

public class HibernateUtility {
	private static SessionFactory sf;

	static {
		sf = new CacheConfiguration().configure().buildSessionFactory();
	}

	public static Session getSession() {

		Session session = sf.openSession();
		return session ;
}

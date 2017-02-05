package com.zte.ums.bn.ip.sqlobj;

import org.h2.jdbcx.JdbcConnectionPool;
import org.skife.jdbi.v2.DBI;

public class JdbiObj {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JdbcConnectionPool ds = JdbcConnectionPool.create("jdbc:h2:mem:test2", "username", "password");
		DBI dbi = new DBI(ds);

		MyDAO dao = dbi.open(MyDAO.class);
		dao.createSomethingTable();
		dao.insert(2, "Aaron");
		String name = dao.findNameById(2);
		
		System.out.println(name);
		dao.deleteByName(name);
		

		dao.close();
		ds.dispose();
	}

}

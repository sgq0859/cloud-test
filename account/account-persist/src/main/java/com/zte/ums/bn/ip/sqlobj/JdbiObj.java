package com.zte.ums.bn.ip.sqlobj;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcConnectionPool;
import org.skife.jdbi.v2.DBI;

import com.zte.ums.bn.ip.Jdbi;

public class JdbiObj {

	public static void main(String[] args) {
		//JdbcConnectionPool ds = JdbcConnectionPool.create("jdbc:h2:mem:test2", "username", "password");
		DataSource ds = Jdbi.getSimpleDataSource();
		DBI dbi = new DBI(ds);

		MyDAO dao = dbi.open(MyDAO.class);
		
		dao.createSomethingTable();
		dao.insert(2, "Aaron");
		String name = dao.findNameById(2);
		
		System.out.println(name);
		//dao.deleteByName(name);
		//dao.deleteSomethingTable();
		

		dao.close();
		//ds.dispose();
		
	}

}

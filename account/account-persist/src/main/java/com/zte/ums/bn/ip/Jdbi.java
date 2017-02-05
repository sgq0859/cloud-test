package com.zte.ums.bn.ip;

import java.util.Map;

import javax.sql.DataSource;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class Jdbi {
	
	 private static final String dirverClassName = "com.mysql.jdbc.Driver"; 
     private static final String url = "jdbc:mysql://127.0.0.1:3306/testdb"; 
     private static final String user = "root"; 
     private static final String pswd = "123456";

	public static void main(String[] args) throws Exception {
		//	Connection conn = DriverManager.getConnection(url, user, pswd); 
		//mysql
		DataSource ds = getSimpleDataSource();
		//h2 database
		//	DataSource ds = JdbcConnectionPool.create("jdbc:h2:mem:test", "username", "password");
		DBI dbi = new DBI(ds);
		Handle h = dbi.open();
		//h.execute("create table something (id int primary key, name varchar(100))");

		//h.execute("insert into something (id, name) values (?, ?)", 1, "Brian");

		Map<String, Object> name = h.createQuery("select name from something where id = :id").bind("id", 1).first();
		System.out.println(name);
		h.close();
	}

	public static DataSource getSimpleDataSource() {
		SimpleDriverDataSource ds= new SimpleDriverDataSource();
		ds.setDriverClass(com.mysql.jdbc.Driver.class);
		ds.setUrl(url);
		ds.setUsername(user);
		ds.setPassword(pswd);
		return ds;
	}

}

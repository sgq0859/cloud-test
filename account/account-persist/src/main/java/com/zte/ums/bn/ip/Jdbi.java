package com.zte.ums.bn.ip;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcConnectionPool;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.util.StringMapper;

public class Jdbi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataSource ds = JdbcConnectionPool.create("jdbc:h2:mem:test", "username", "password");
		DBI dbi = new DBI(ds);
		Handle h = dbi.open();
		h.execute("create table something (id int primary key, name varchar(100))");

		h.execute("insert into something (id, name) values (?, ?)", 1, "Brian");

		String name = h.createQuery("select name from something where id = :id").bind("id", 1).map(StringMapper.FIRST)
				.first();
		System.out.println(name);
		h.close();
	}

}

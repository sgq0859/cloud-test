package com.zte.ums.bn.ip.sqlobj;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface MyDAO {
	
	@SqlUpdate("create table something (id int primary key, name varchar(100))")
	void createSomethingTable();

	@SqlUpdate("insert into something (id, name) values (:id, :name)")
	void insert(@Bind("id") int id, @Bind("name") String name);

	@SqlQuery("select name from something where id = :id")
	String findNameById(@Bind("id") int id);

	@SqlUpdate("delete from something where name = :it")
	void deleteByName(@Bind String name);
	
	@SqlUpdate("insert into something (id, name) values (:id, :name)")
	void insertBean(@BindBean Something s);
	
	@SqlUpdate("update something set name = :s.name where id = :s.id")
	void update(@BindBean("s") Something something);
	
	/**
	 * close with no args is used to close the connection
	 */
	void close();
}


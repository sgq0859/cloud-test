package com.zte.ums.bn.ip.sqlobj;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.skife.jdbi.v2.SQLStatement;
import org.skife.jdbi.v2.sqlobject.Binder;
import org.skife.jdbi.v2.sqlobject.BinderFactory;
import org.skife.jdbi.v2.sqlobject.BindingAnnotation;

@BindingAnnotation(BindSomething.SomethingBinderFactory.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface BindSomething 
{ 
  public static class SomethingBinderFactory implements BinderFactory
  {
    public Binder build(Annotation annotation)
    {
      return new Binder<BindSomething, Something>()
      {
		public void bind(SQLStatement<?> q, BindSomething arg1, Something arg) {
			 q.bind("ident", arg.getId());
	         q.bind("nom", arg.getName());
		}
      };
    }
  }
}

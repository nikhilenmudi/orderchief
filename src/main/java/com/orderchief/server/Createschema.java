package com.orderchief.server;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;




import com.orderchief.domain.Product;
import com.orderchief.domain.ProductOption;
import com.orderchief.domain.ProductSubOption;

public class Createschema {

	public static void main(String[] args) {
		
		Product product = new Product();
		product.setName("Coffee");
		
		ProductOption po = new ProductOption();
		po.setTopping("whipcream");
		
		
		
		ProductSubOption pso =  new ProductSubOption();
		pso.setSize("medium");
		
		
		product.getOption().add(po);
		product.getProductSubOption().add(pso);
		
		
		System.out.println("schema!");
		Configuration configuration = new Configuration().configure();

		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());

		SessionFactory factory = configuration.buildSessionFactory(builder.build());
		
		Session session = factory.openSession();
		session.beginTransaction();
		session.save(product);
		session.getTransaction().commit();
		session.close();
		
		
	}

}

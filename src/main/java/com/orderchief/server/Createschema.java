package com.orderchief.server;

import java.util.ArrayList;
import java.util.List;

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
		
		ProductOption po2 = new ProductOption();
		po2.setTopping("barbeque");
		ProductOption po = new ProductOption();
		po.setTopping("whipcream");
		ProductOption po1 = new ProductOption();
		po1.setTopping("honeymustard");
		
		ProductSubOption ps1 = new ProductSubOption();
		ps1.setSize("Small");
		
		ProductSubOption ps2 = new ProductSubOption();
		ps2.setSize("Medium");
		
		ProductSubOption ps3 = new ProductSubOption();
		ps3.setSize("Large");
		
		List<ProductOption> productoptions = new ArrayList<ProductOption>();
		List<ProductSubOption> productsuboptions = new ArrayList<ProductSubOption>();
		
		productoptions.add(po1);
		productoptions.add(po2);
		productoptions.add(po);
		
		productsuboptions.add(ps1);
		productsuboptions.add(ps2);
		productsuboptions.add(ps3);
		
		po.setProduct(product);
		po1.setProduct(product);
		po2.setProduct(product);
		
		ps1.setProduct(product);
		ps2.setProduct(product);
		ps3.setProduct(product);
		
		product.setProductoption(productoptions);
		product.setProductSubOption(productsuboptions);
		
		System.out.println("schema!");
		Configuration configuration = new Configuration().configure();

		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());

		SessionFactory factory = configuration.buildSessionFactory(builder.build());
		
		Session session = factory.openSession();
		session.beginTransaction();
		
		session.save(product);
		session.getTransaction().commit();
		session.close();
		System.out.println(product.getName());
		
		for(int i=0;i<product.getProductoption().size();i++){
		System.out.println(product.getProductOptionsAsListOfStrings().get(i));	
		System.out.println(product.getProductoption().get(i).getTopping());
		}
		
	}

}

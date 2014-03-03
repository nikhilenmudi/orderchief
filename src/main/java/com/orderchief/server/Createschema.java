package com.orderchief.server;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;










import com.orderchief.domain.Order;
import com.orderchief.domain.Product;
import com.orderchief.domain.ProductOption;
import com.orderchief.domain.ProductSubOption;
import com.orderchief.domain.Vendor;


public class Createschema {

	public static void main(String[] args) {
		
		
		System.out.println("schema!");
		Configuration configuration = new Configuration().configure();

		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());

		SessionFactory factory = configuration.buildSessionFactory(builder.build());
		
		Session session = factory.openSession();
		session.beginTransaction();
		//create vendor named tims
		Vendor tims =  new Vendor();
		tims.setVendorName("TIM HORTON");
		//create product coffee
		Product product = new Product();
		product.setName("Coffee");
		product.setBaseprice(BigDecimal.valueOf(2.0));
		//create product burger
		Product product1 = new Product();
		product1.setName("Burger");
		product1.setBaseprice(BigDecimal.valueOf(2.0));
		//create productoptions
		ProductOption po2 = new ProductOption();
		po2.setTopping("barbeque");
		po2.setBaseprice(BigDecimal.valueOf(0.5));
		ProductOption po = new ProductOption();
		po.setTopping("whipcream");
		po.setBaseprice(BigDecimal.valueOf(0.5));
		ProductOption po1 = new ProductOption();
		po1.setTopping("honeymustard");
		po1.setBaseprice(BigDecimal.valueOf(0.3));
		
		
		ProductOption po11 = new ProductOption();
		po11.setTopping("mayo");
		po11.setBaseprice(BigDecimal.valueOf(0.5));
		ProductOption po22 = new ProductOption();
		po22.setTopping("onion");
		po22.setBaseprice(BigDecimal.valueOf(0.5));
		//create productsuboptions
		ProductSubOption ps1 = new ProductSubOption();
		ps1.setSize("Small");
		ps1.setBaseprice(BigDecimal.valueOf(0.5));
		
		ProductSubOption ps11 = new ProductSubOption();
		ps11.setSize("Small");
		ps11.setBaseprice(BigDecimal.valueOf(0.5));
		ProductSubOption ps2 = new ProductSubOption();
		ps2.setSize("Medium");
		ps2.setBaseprice(BigDecimal.valueOf(0.5));
		ProductSubOption ps22 = new ProductSubOption();
		ps22.setSize("Medium");
		ps22.setBaseprice(BigDecimal.valueOf(0.5));
		ProductSubOption ps3 = new ProductSubOption();
		ps3.setSize("Large");
		ps3.setBaseprice(BigDecimal.valueOf(0.5));
		
		
		
		List<ProductOption> productoptions = new ArrayList<ProductOption>();
		List<ProductSubOption> productsuboptions = new ArrayList<ProductSubOption>();
		
		List<ProductOption> productoptions1 = new ArrayList<ProductOption>();
		List<ProductSubOption> productsuboptions1 = new ArrayList<ProductSubOption>();
		
		productoptions.add(po1);
		productoptions.add(po2);
		productoptions.add(po);
		
		productsuboptions.add(ps1);
		productsuboptions.add(ps2);
		productsuboptions.add(ps3);
		
		productoptions1.add(po11);
		productoptions1.add(po22);
		
		productsuboptions.add(ps11);
		productsuboptions.add(ps22);
		
		
		po.setProduct(product);
		po1.setProduct(product);
		po2.setProduct(product);
		
		po11.setProduct(product1);
		po22.setProduct(product1);
		
		
		ps1.setProduct(product);
		ps2.setProduct(product);
		ps3.setProduct(product);
		
		ps11.setProduct(product1);
		ps22.setProduct(product1);
		
		
		product.setProductoption(productoptions);
		product.setProductSubOption(productsuboptions);
		
		product1.setProductoption(productoptions1);
		product1.setProductSubOption(productsuboptions1);
		
		List<Product> productlist = new ArrayList<Product>();
		productlist.add(product);
		productlist.add(product1);
		
		tims.setProduct(productlist);
		product.setVendor(tims);
		product1.setVendor(tims);
		
		Order order1 = new Order();
		order1.setProduct(productlist);
		order1.setVendor(tims);
		
		
		session.save(product);
		session.save(product1);
		session.save(tims);
		session.save(order1);
		session.getTransaction().commit();
		session.close();
		System.out.println(product.getName());
		
		for(int i=0;i<product.getProductoption().size();i++){
//		System.out.println(product.getProductOptionsAsListOfStrings().get(i));	
//		System.out.println(product.getProductoption().get(i).getTopping());
//		System.out.println(order1.calcOrderTotal());
		}
		
	}

}

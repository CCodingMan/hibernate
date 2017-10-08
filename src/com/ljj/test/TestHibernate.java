package com.ljj.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.ljj.pojo.Category;
import com.ljj.pojo.Product;

public class TestHibernate {

	public static void main(String[] args) {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();
        
        //插入数据
//        insert(s);
        
        //通过ID查询
//        qryById(s);
        
        //通过ID获取对象再删除
//		deleteById(s);
        
        //通过ID获取对象，set内容，再update
//        modifyById(s);
        
        //使用hql模糊查询iPhone
//        hqlQry(s);
        
        //使用Criteria进行数据查询
//        criteriaQry(s);
        
        //标准sql语句查询,如果只查询一个表，那就别用标准SQL语句,标准SQL语句是给某些特殊场合，需要关联多个表查询用的,标准SQL语句的结果不能直接注入一个实体类，因为标准SQL语句有可能是同时查询几个表，是没有办法插入一个实体类的。
//        sqlQry(s);
        
        s.getTransaction().commit();
        //Session关闭了与Session失去了联系，相当于脱离了管理，状态就是脱管的
        s.close();
        sf.close();
	}

	/** 
	 * @Title: sqlQry 
	 * @Description: 标准sql语句查询,如果只查询一个表，那就别用标准SQL语句,
	 * 标准SQL语句是给某些特殊场合，需要关联多个表查询用的,
	 * 标准SQL语句的结果不能直接注入一个实体类，因为标准SQL语句有可能是同时查询几个表，
	 * 是没有办法插入一个实体类的
	 * @param @param s
	 * @param @throws HibernateException 
	 * @return void 
	 * @throws 
	 */
	public static void sqlQry(Session s) throws HibernateException {
		String name = "iphone";
        String sql = "select * from Product_ p where p.name like '%"+name+"%'";
        Query q = s.createSQLQuery(sql);
        List<Object[]> list = q.list();
        for (Object[] os : list) {
			for (Object field : os) {
				System.out.println(field+"\t");
			}
			System.out.println();
		}
	}

	/** 
	 * @Title: criteriaQry 
	 * @Description: TODO
	 * @param @param s
	 * @param @throws HibernateException 
	 * @return void 
	 * @throws 
	 */
	public static void criteriaQry(Session s) throws HibernateException {
		String name = "iphone";
        Criteria c = s.createCriteria(Product.class);
        c.add(Restrictions.ilike("name", "%"+name+"%"));
        List<Product> list = c.list();
        for (Product product : list) {
			System.out.println(product.getName());
		}
	}

	/** 
	 * @Title: hqlQry 
	 * @Description: 使用hql模糊查询iPhone
	 * @param @param s
	 * @param @throws HibernateException 
	 * @return void 
	 * @throws 
	 */
	public static void hqlQry(Session s) throws HibernateException {
		String name = "iphone";
        Query q = s.createQuery("from Product p where p.name like ?");
        //'%',sql模糊查询表示任意0个或多个字符， _ 表示任意单个字符,
        //[] 表示括号内所列字符中的一个， [^ ] 表示不在括号所列之内的单个字符
        q.setString(0, "%"+name+"%");
        List<Product> list = q.list();
        for (Product product : list) {
			System.out.println(product.getName());
		}
	}

	/** 
	 * @Title: modifyById 
	 * @Description: 通过ID获取对象，set内容，再update
	 * @param @param s
	 * @param @throws HibernateException 
	 * @return void 
	 * @throws 
	 */
	public static void modifyById(Session s) throws HibernateException {
		//通过ID获取对象
        Product p = (Product) s.get(Product.class, 6);
        p.setName("iphone-modify");
        s.update(p);
	}

	/** 
	 * @Title: deleteById 
	 * @Description: 通过ID获取对象再删除
	 * @param @param s
	 * @param @throws HibernateException 
	 * @return void 
	 * @throws 
	 */
	public static void deleteById(Session s) throws HibernateException {
		//通过ID获取对象
        Product p = (Product) s.get(Product.class, 6);
        s.delete(p);
	}

	/** 
	 * @Title: qryById 
	 * @Description: 通过ID查询
	 * @param @param s
	 * @param @throws HibernateException 
	 * @return void 
	 * @throws 
	 */
	public static void qryById(Session s) throws HibernateException {
		//通过ID获取对象
        Product p = (Product) s.get(Product.class, 6);
        System.out.println("id=5的产品是："+p.getName());
	}

	/** 
	 * @Title: insert 
	 * @Description: 插入数据
	 * @param @param s
	 * @param @throws HibernateException 
	 * @return void 
	 * @throws 
	 */
	public static void insert(Session s) throws HibernateException {
		for(int i=0; i<10; i++){
        	//new 了一个Product();，在数据库中还没有对应的记录，这个时候Product对象的状态是瞬时的
        	Product p = new Product();
        	p.setName("iphone7"+i);
        	p.setPrice(i);
        	
        	//通过Session的save把该对象保存在了数据库中，该对象也和Session之间产生了联系，此时状态是持久的。
        	s.save(p);
        }
        
//        Category c = new Category();
//        c.setName("分类1");
//        s.save(c);
	}

}

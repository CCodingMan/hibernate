package com.ljj.test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.ljj.pojo.Category;
import com.ljj.pojo.Product;
import com.ljj.pojo.User;

public class TestHibernate {

	public static void main(String[] args) {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();
        
        //��������
//        insert(s);
        
        //ͨ��ID��ѯ
//        qryById(s);
        
        //ͨ��ID��ȡ������ɾ��
//		deleteById(s);
        
        //ͨ��ID��ȡ����set���ݣ���update
//        modifyById(s);
        
        //ʹ��hqlģ����ѯiPhone
//        hqlQry(s);
        
        //ʹ��Criteria�������ݲ�ѯ
//        criteriaQry(s);
        
        //��׼sql����ѯ,���ֻ��ѯһ�����Ǿͱ��ñ�׼SQL���,��׼SQL����Ǹ�ĳЩ���ⳡ�ϣ���Ҫ����������ѯ�õ�,��׼SQL���Ľ������ֱ��ע��һ��ʵ���࣬��Ϊ��׼SQL����п�����ͬʱ��ѯ��������û�а취����һ��ʵ����ġ�
//        sqlQry(s);
        
        //���һ��ϵ
//        manyToOne(s);
        
        //һ�Զ��ϵ����ȡid=2�����µ�������Ʒ
//        oneToMany(s);
        
        //��Զ��ϵ
//        manyToMany(s);
        
        s.getTransaction().commit();
        //Session�ر�����Sessionʧȥ����ϵ���൱�������˹���״̬�����ѹܵ�
        s.close();
        sf.close();
	}

	/** 
	 * @Title: manyToMany 
	 * @Description: ��Զ��ϵ
	 * @param @param s
	 * @param @throws HibernateException 
	 * @return void 
	 * @throws 
	 */
	public static void manyToMany(Session s) throws HibernateException {
		Set<User> users = new HashSet<>();
        for (int i = 0; i < 3; i++) {
			User u = new User();
			u.setName("user"+i);
			users.add(u);
			s.save(u);
		}
        
        Product p = (Product) s.get(Product.class, 1);
        p.setUsers(users);
        s.save(p);
	}

	/** 
	 * @Title: oneToMany 
	 * @Description: һ�Զ��ϵ
	 * @param @param s
	 * @param @throws HibernateException 
	 * @return void 
	 * @throws 
	 */
	public static void oneToMany(Session s) throws HibernateException {
		Category c = (Category) s.get(Category.class, 2);
        Set<Product> sp = c.getProducts();
        for (Product p : sp) {
        	System.out.println(p.getName());
		}
	}

	/** 
	 * @Title: manyToOne 
	 * @Description: ���һ��ϵ
	 * ͨ�����һ��ϵ��������id=8�Ĳ�Ʒ��Ӧ id=1�ķ���
	 * @param @param s
	 * @param @throws HibernateException 
	 * @return void 
	 * @throws 
	 */
	public static void manyToOne(Session s) throws HibernateException {
		Category c =new Category();
        c.setName("c1");
        s.save(c);
        
        Product p = (Product) s.get(Product.class, 8);
        p.setCategory(c);
        s.update(p);
	}

	/** 
	 * @Title: sqlQry 
	 * @Description: ��׼sql����ѯ,���ֻ��ѯһ�����Ǿͱ��ñ�׼SQL���,
	 * ��׼SQL����Ǹ�ĳЩ���ⳡ�ϣ���Ҫ����������ѯ�õ�,
	 * ��׼SQL���Ľ������ֱ��ע��һ��ʵ���࣬��Ϊ��׼SQL����п�����ͬʱ��ѯ������
	 * ��û�а취����һ��ʵ�����
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
	 * @Description: ʹ��hqlģ����ѯiPhone
	 * @param @param s
	 * @param @throws HibernateException 
	 * @return void 
	 * @throws 
	 */
	public static void hqlQry(Session s) throws HibernateException {
		String name = "iphone";
        Query q = s.createQuery("from Product p where p.name like ?");
        //'%',sqlģ����ѯ��ʾ����0�������ַ��� _ ��ʾ���ⵥ���ַ�,
        //[] ��ʾ�����������ַ��е�һ���� [^ ] ��ʾ������������֮�ڵĵ����ַ�
        q.setString(0, "%"+name+"%");
        List<Product> list = q.list();
        for (Product product : list) {
			System.out.println(product.getName());
		}
	}

	/** 
	 * @Title: modifyById 
	 * @Description: ͨ��ID��ȡ����set���ݣ���update
	 * @param @param s
	 * @param @throws HibernateException 
	 * @return void 
	 * @throws 
	 */
	public static void modifyById(Session s) throws HibernateException {
		//ͨ��ID��ȡ����
        Product p = (Product) s.get(Product.class, 6);
        p.setName("iphone-modify");
        s.update(p);
	}

	/** 
	 * @Title: deleteById 
	 * @Description: ͨ��ID��ȡ������ɾ��
	 * @param @param s
	 * @param @throws HibernateException 
	 * @return void 
	 * @throws 
	 */
	public static void deleteById(Session s) throws HibernateException {
		//ͨ��ID��ȡ����
        Product p = (Product) s.get(Product.class, 6);
        s.delete(p);
	}

	/** 
	 * @Title: qryById 
	 * @Description: ͨ��ID��ѯ
	 * @param @param s
	 * @param @throws HibernateException 
	 * @return void 
	 * @throws 
	 */
	public static void qryById(Session s) throws HibernateException {
		//ͨ��ID��ȡ����
        Product p = (Product) s.get(Product.class, 6);
        System.out.println("id=5�Ĳ�Ʒ�ǣ�"+p.getName());
	}

	/** 
	 * @Title: insert 
	 * @Description: ��������
	 * @param @param s
	 * @param @throws HibernateException 
	 * @return void 
	 * @throws 
	 */
	public static void insert(Session s) throws HibernateException {
		for(int i=0; i<10; i++){
        	//new ��һ��Product();�������ݿ��л�û�ж�Ӧ�ļ�¼�����ʱ��Product�����״̬��˲ʱ��
        	Product p = new Product();
        	p.setName("iphone7"+i);
        	p.setPrice(i);
        	
        	//ͨ��Session��save�Ѹö��󱣴��������ݿ��У��ö���Ҳ��Session֮���������ϵ����ʱ״̬�ǳ־õġ�
        	s.save(p);
        }
        
//        Category c = new Category();
//        c.setName("����1");
//        s.save(c);
	}

}

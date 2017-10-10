package com.ljj.test;

import java.util.HashSet;
import java.util.Iterator;
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
		SessionFactory sf = 
				new Configuration().configure().buildSessionFactory();
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
        
        //��׼sql����ѯ,���ֻ��ѯһ�����Ǿͱ��ñ�׼SQL���,
        //��׼SQL����Ǹ�ĳЩ���ⳡ�ϣ���Ҫ����������ѯ�õ�,
        //��׼SQL���Ľ������ֱ��ע��һ��ʵ���࣬��Ϊ��׼SQL����п�����ͬʱ��ѯ������
        //��û�а취����һ��ʵ����ġ�
//        sqlQry(s);
        
        //���һ��ϵ
//        manyToOne(s);
        
        //һ�Զ��ϵ����ȡid=2�����µ�������Ʒ
//        oneToMany(s);
        
        //��Զ��ϵ
//        manyToMany(s);
        
        //���񣬱����ӣ�ִ������������
//        transaction(s);
        
        //���Ե��ӳټ���
//        attributeLazyload(s);
        
        //��ϵ�ӳټ����ֽ�lazyload��
        //��one-many many-many��ʱ�򶼿���ʹ�ù�ϵ���ӳټ���	
//        relationLazyload(s);
        
        //������δ����ʱɾ�����࣬���Ӧ�Ĳ�Ʒ���ᱻɾ���������˼�����
        //ɾ�������Ӧ�Ĳ�Ʒ���ᱻɾ����
//        deleteCascade(s);
        
        //һ�����棬Ĭ�Ͽ�����һ����������session��,����Ҫ��xml������
//        oneLevelCache(s); 
        
        //��������,������������SessionFactory��,��Ҫ��xml������,
        //hibernate�����ṩ�������棬����ʹ�õ������Ķ���������
        //����ʹ�õ��� EhCache�ṩ�Ķ�������,��Ҫ��src�´���һ��
        //ehcache.xml����EHCache�Ļ�������
//        twoLevelCache(sf, s);
        
        //��ҳ��ѯ
//        pageQry(s);
        
        //���ֲ�ѯ��ȡ��ʽ�Ա�load VS get
        //load�ӳټ������Ա����ʲŵ���SQL��get���ӳټ��������ۺ����Ƿ���������������sql
        //��id�����ڣ�load���ط�ʽ���׳��쳣��get���ط�ʽ�᷵��null
//        twoLoadWay(s);
        
        //����session��ʽ
//        Hibernate�����ַ�ʽ���session,�ֱ��ǣ� 
//        openSession��getCurrentSession 
//        ���ǵ��������� 
//        1. ��ȡ���Ƿ���ͬһ��session���� 
//        openSessionÿ�ζ���õ�һ���µ�Session���� ��
//        getCurrentSession��ͬһ���߳��У�ÿ�ζ��ǻ�ȡ��ͬ��Session����
//        �����ڲ�ͬ���߳��л�ȡ���ǲ�ͬ��Session���� 
//        2. �����ύ�ı�Ҫ�� 
//        openSessionֻ�������ӣ�ɾ�����޸ĵ�ʱ����Ҫ���񣬲�ѯʱ����Ҫ�� ��
//        getCurrentSession�����в�����������������н��У������ύ�����
//        session���Զ��رգ����ܹ��ٽ��йرա�
          
        //N+1
//        nQry(s);
        
        //��ѯ����
//		qryTotal(s);
        
        //�ֹ���
/*        ���������ⴴ��һ�����������������ݡ�
            1. ͨ��session1�õ�id=1�Ķ��� product1
            2. ��product1ԭ���۸�Ļ���������1000
            3. ����product1֮ǰ��ͨ��session2�õ�id=1�Ķ���product2
            4. ��product2ԭ���۸�Ļ���������1000
            5. ����product1
            6. ����product2
                          �������product�ļ۸�ֻ������1000��������2000
                      ����취��
             1.����һ��version�ֶΣ����ڰ汾��Ϣ���ơ�������ֹ����ĺ��Ļ��ơ�
               <version name="version" column="ver" type="int"></version>
                                ע�⣺ versionԪ�ر��������id���棬��������
             2.�޸� Product.java  ����version����
*/
//        happyLock(sf, s);
        
        //c3p0���ӳ�
/*      �������ݿ�����ʱ�Ƚ�����ʱ��ģ�����ͨ������������ݿ����ӳصļ����������������ݿ����ӣ�
                 �����ڽ�������ʹ�ã��Ӷ���Լ���������ݿ����ӵ�ʱ�� 
        hibernate�������ṩ�����ݿ����ӳصģ�
                 ����hibernate����Ҳ���Ƽ�ʹ�����Դ������ݿ����ӳء� 
                 һ�㶼��ʹ�õ����������ݿ����ӳ� 
        C3P0����ѵĵ����������ݿ����ӳأ������в���ı��� 
                  ע�������д��������ʱ�򣬴�����Ч�����������ǿ���������ġ� ֻ���ڸ߲�����������£�
                  �Ż�����������֪ʶ��Ҫ���ṩ���������ð취���Թ��Ժ�����Ҫ��ʱ�򣬲�ѯ���޸ķ��㡣
        */
        connectionPool(s);
        
        s.getTransaction().commit();
        //Session�ر�����Sessionʧȥ����ϵ���൱�������˹���״̬�����ѹܵ�
        s.close();
        sf.close();
	}

	/** 
	 * @Title: connectionPool 
	 * @Description: ���ӳ�
	 * 1.hibernate.cfg.xml�������ӳأ�ȥ��������������
	 * 2.ӳ��xml�ļ�ȥ��������������<cache usage="read-only" />
	 * @param @param s
	 * @param @throws HibernateException 
	 * @return void 
	 * @throws 
	 */
	public static void connectionPool(Session s) throws HibernateException {
		s.createQuery("from Category").list();
	}

	/** 
	 * @Title: happyLock 
	 * @Description: �ֹ���
	 * @param @param sf
	 * @param @param s
	 * @param @throws HibernateException 
	 * @return void 
	 * @throws 
	 */
	public static void happyLock(SessionFactory sf, Session s) throws HibernateException {
		Session s2 = sf.openSession();
        s2.beginTransaction();
        Product p1 = (Product) s.get(Product.class, 1);
        System.out.println("��Ʒԭ���۸���: " + p1.getPrice());
        p1.setPrice(p1.getPrice() + 1000);
        Product p2 = (Product) s2.get(Product.class, 1);
        p2.setPrice(p2.getPrice() + 1000);
        s.update(p1);
        s2.update(p2);
        s2.getTransaction().commit();
        Product p = (Product) s.get(Product.class, 1);
        System.out.println("�������μ۸����Ӻ󣬼۸��Ϊ: " + p.getPrice());
        s2.close();
	}

	/** 
	 * @Title: qryTotal 
	 * @Description: ��ѯ����
	 * select count(*) from ....����һ��Query����
	 * ����Query�����uniqueResult()����������һ��long�͵����ݣ�����ѯ����
	 * @param @param s
	 * @param @throws HibernateException 
	 * @return void 
	 * @throws 
	 */
	public static void qryTotal(Session s) throws HibernateException {
		String name = "iphone";
        Query q = s.createQuery("select count(*) from Product p "
        		+ "where p.name like ?");
        q.setString(0, "%"+name+"%");
        long total = (long) q.uniqueResult();
        System.out.println(total);
	}

	/** 
	 * @Title: nQry 
	 * @Description: N+1
	 * Hibernate�л�����ƣ�����ͨ����id��Ϊkey��product���󱣴��ڻ����� 
     * ͬʱhibernateҲ�ṩQuery�Ĳ�ѯ��ʽ���������ݿ�����100����¼��
     * ������30����¼�ڻ����У�����ʹ��Query�ĵ�list������
     * �ͻ����е�100�����ݶ������ݿ��в�ѯ����������30�������еļ�¼ 
     * N+1��ʲô��˼�أ�����ִ��һ��sql��䣬ȥ��ѯ��100����¼�����ǣ�
     * ֻ������100����¼��ID 
     * Ȼ���ٸ���id,���н�һ����ѯ�� 
     * ���id�ڻ����У��ʹӻ����л�ȡproduct�����ˣ������ٴ����ݿ��л�ȡ.
     * ����ͨ��Query��iterator����������������Product��id�����
     * Ȼ����ͨ��it.next()��ѯÿһ������
     * �����������ڻ����У���ֱ�Ӵӻ�����ȡ��
     * ����ʹ����ݿ��л�ȡ
     * N+1�е�1������ָֻ����id��SQL��䣬Nָ��������ڻ������Ҳ�����Ӧ�����ݣ�
     * �͵����ݿ���ȥ��
	 * @param @param s
	 * @param @throws HibernateException 
	 * @return void 
	 * @throws 
	 */
	public static void nQry(Session s) throws HibernateException {
		String name = "iphone";
        Query q = s.createQuery("from Product p where p.name like ?");
        q.setString(0, "%"+name+"%");
        Iterator<Product> it = q.iterate();
        while(it.hasNext()){
        	Product p = it.next();
        	System.out.println(p.getName());
        }
	}

	/** 
	 * @Title: twoLoadWay 
	 * @Description: load VS get
	 * load�ӳټ������Ա����ʲŵ���SQL��get���ӳټ��������ۺ����Ƿ���������������sql
     * ��id�����ڣ�load���ط�ʽ���׳��쳣��get���ط�ʽ�᷵��null
	 * @param @param s
	 * @param @throws HibernateException 
	 * @return void 
	 * @throws 
	 */
	public static void twoLoadWay(Session s) throws HibernateException {
		System.out.println("log1");
        Product p = (Product)s.get(Product.class, 5);
        System.out.println("log2");
        Product p2 = (Product)s.load(Product.class, 5);
        System.out.println("log3");
         
        Product p3 = (Product)s.get(Product.class, 500);
        System.out.println("p3="+p3);
         
        Product p4 = (Product)s.load(Product.class, 500);
        System.out.println("p3="+p4);
	}

	/** 
	 * @Title: pageQry 
	 * @Description: ��ҳ��ѯ
	 * @param @param s
	 * @param @throws HibernateException 
	 * @return void 
	 * @throws 
	 */
	public static void pageQry(Session s) throws HibernateException {
		String name = "iphone";
        Criteria c = s.createCriteria(Product.class);
        c.add(Restrictions.like("name", "%"+name+"%"));
        //��ʾ�ӵ�2�����ݿ�ʼ,��0
        c.setFirstResult(0);
        //��ʾһ����ѯ5������
        c.setMaxResults(5);
        
        List<Product> ps = c.list();
        for (Product p : ps) {
            System.out.println(p.getName());
        }
	}

	/** 
	 * @Title: twoLevelCache 
	 * @Description: ��������,������������SessionFactory��,��Ҫ��xml������,
	 * hibernate�����ṩ�������棬����ʹ�õ������Ķ���������
	 * ����ʹ�õ��� EhCache�ṩ�Ķ�������
	 * @param @param sf
	 * @param @param s
	 * @param @throws HibernateException 
	 * @return void 
	 * @throws 
	 */
	public static void twoLevelCache(SessionFactory sf, Session s) 
			throws HibernateException {
		Category p1 = (Category) s.get(Category.class, 2);
        Category p2 = (Category) s.get(Category.class, 2);
        
        //ע�⣺���ǵڶ���session
        Session s2 = sf.openSession();
        s2.beginTransaction();
        Category p3 = (Category) s2.get(Category.class, 2);
        s2.getTransaction().commit();
        s2.close();
	}

	/** 
	 * @Title: oneLevelCache 
	 * @Description: һ�����棬Ĭ�Ͽ�����һ����������session��
	 * @param @param s
	 * @param @throws HibernateException 
	 * @return void 
	 * @throws 
	 */
	public static void oneLevelCache(Session s) 
			throws HibernateException {
		System.out.println("log1");
        //��һ�β�ѯ��SQL��䣬����ѯ
        Category c1 = (Category)s.get(Category.class, 1);
        System.out.println("log2");
        //�ڶ��β�ѯ��SQL��䣬��session�л�ȡ
        Category c2= (Category)s.get(Category.class, 1);
        System.out.println("log3");
	}

	/** 
	 * @Title: deleteCascade 
	 * @Description: ɾ������
	 * cascade="delete"������
     *  all�����в�����ִ�м������������� delete+save-update����
     *  none�����в�������ִ�м���������Ĭ�ϣ���
     *  delete��ɾ��ʱִ�м�������(һ�Զ�)�� 
     *  save-update������͸���ʱִ�м���������һ�Զࣩ��
     *  ����ͨ������one-many��many-to-many�ϣ�����������many-one��
	 * @param @param s
	 * @param @throws HibernateException 
	 * @return void 
	 * @throws 
	 */
	public static void deleteCascade(Session s) 
			throws HibernateException {
		//ɾ������
        Category c = (Category) s.get(Category.class, 2);
        s.delete(c);
	}

	/** 
	 * @Title: relationLazyload 
	 * @Description: ��ϵ�ӳټ��أ���one-many many-many��ʱ�򶼿���ʹ�ù�ϵ���ӳټ���
	 * �Ȳ�ѯcategory������Ҫproductʱ���ٲ����ݿ���product����,xmlӳ���ļ�lazy=��true��
	 * @param @param s
	 * @param @throws HibernateException 
	 * @return void 
	 * @throws 
	 */
	public static void relationLazyload(Session s) 
			throws HibernateException {
		Category c = (Category) s.get(Category.class, 2);
        System.out.println("log1");
        System.out.println(c.getProducts());
        System.out.println("log2");
	}

	/** 
	 * @Title: attributeLazyload 
	 * @Description: ���Ե��ӳټ��أ�session.load(Product.class,1);
	 * �ڴ�ӡlog1֮ǰ���ǲ����ӡ��sql���ģ�ֻ���ڷ��ʷ������ԡ�getName()"��ʱ��
	 * �Ż�������ݿ�
	 * @param @param s
	 * @param @throws HibernateException 
	 * @return void 
	 * @throws 
	 */
	public static void attributeLazyload(Session s) 
			throws HibernateException {
		Product p = (Product) s.load(Product.class, 1);
        System.out.println("log1");
        System.out.println(p.getName());
        System.out.println("log2");
	}

	/** 
	 * @Title: transaction 
	 * @Description: ���񣬵�һ����ɾ��id=1�Ĳ�Ʒ������ǻ�ɹ���
     *  �ڶ������޸�id=2�Ĳ�Ʒ��ʹ�����Ʒ���Ƴ��������ݿ������õĳ���30������ǻ�ʧ�ܵġ�
     *  ��Ϊ����������������һ�������У����ҵڶ�������ʧ���ˣ��������Ľ��������������û����Ч
     *  ��ע�⡱����Mysql�У�ֻ�е����������INNODB��ʱ�򣬲�֧������
     *  �޸ı������ΪINNODB��SQL��alter table product_ ENGINE  = innodb;
     *  �鿴������͵�SQL��show table status from test; 
	 * @param @param s
	 * @param @throws HibernateException 
	 * @return void 
	 * @throws 
	 */
	public static void transaction(Session s) 
			throws HibernateException {
		Product p = (Product) s.get(Product.class, 1);
        s.delete(p);
        
        Product p2 = (Product) s.get(Product.class, 2);
        p2.setName("�޸�id=2�Ĳ�Ʒ��ʹ�����Ʒ���Ƴ��������ݿ������õĳ���30��"
        		+ "����ǻ�ʧ�ܵġ�");
        s.update(p2);
	}

	/** 
	 * @Title: manyToMany 
	 * @Description: ��Զ��ϵ
	 * @param @param s
	 * @param @throws HibernateException 
	 * @return void 
	 * @throws 
	 */
	public static void manyToMany(Session s) 
			throws HibernateException {
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
        String sql = 
        		"select * from Product_ p where p.name like '%"+name+"%'";
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
        	//new ��һ��Product();�������ݿ��л�û�ж�Ӧ�ļ�¼��
			//���ʱ��Product�����״̬��˲ʱ��
        	Product p = new Product();
        	p.setName("iphone7"+i);
        	p.setPrice(i);
        	
        	//ͨ��Session��save�Ѹö��󱣴��������ݿ��У�
        	//�ö���Ҳ��Session֮���������ϵ����ʱ״̬�ǳ־õġ�
        	s.save(p);
        }
        
//        Category c = new Category();
//        c.setName("����1");
//        s.save(c);
	}

}

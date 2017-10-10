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
        
        //标准sql语句查询,如果只查询一个表，那就别用标准SQL语句,
        //标准SQL语句是给某些特殊场合，需要关联多个表查询用的,
        //标准SQL语句的结果不能直接注入一个实体类，因为标准SQL语句有可能是同时查询几个表，
        //是没有办法插入一个实体类的。
//        sqlQry(s);
        
        //多对一关系
//        manyToOne(s);
        
        //一对多关系，获取id=2分类下的所有商品
//        oneToMany(s);
        
        //多对多关系
//        manyToMany(s);
        
        //事务，本例子，执行了两个操作
//        transaction(s);
        
        //属性的延迟加载
//        attributeLazyload(s);
        
        //关系延迟加载又叫lazyload，
        //在one-many many-many的时候都可以使用关系的延迟加载	
//        relationLazyload(s);
        
        //级联，未配置时删除分类，其对应的产品不会被删除。配置了级联，
        //删除分类对应的产品都会被删除掉
//        deleteCascade(s);
        
        //一级缓存，默认开启，一级缓存存放在session上,不需要在xml上配置
//        oneLevelCache(s); 
        
        //二级缓存,二级缓存是在SessionFactory上,需要在xml上配置,
        //hibernate本身不提供二级缓存，都是使用第三方的二级缓存插件
        //这里使用的是 EhCache提供的二级缓存,需要在src下创建一个
        //ehcache.xml用于EHCache的缓存配置
//        twoLevelCache(sf, s);
        
        //分页查询
//        pageQry(s);
        
        //两种查询获取方式对比load VS get
        //load延迟加载属性被访问才调用SQL，get非延迟加载立无论后面是否访问属性立马调用sql
        //当id不存在，load加载方式会抛出异常，get加载方式会返回null
//        twoLoadWay(s);
        
        //两种session方式
//        Hibernate有两种方式获得session,分别是： 
//        openSession和getCurrentSession 
//        他们的区别在于 
//        1. 获取的是否是同一个session对象 
//        openSession每次都会得到一个新的Session对象 。
//        getCurrentSession在同一个线程中，每次都是获取相同的Session对象，
//        但是在不同的线程中获取的是不同的Session对象 
//        2. 事务提交的必要性 
//        openSession只有在增加，删除，修改的时候需要事务，查询时不需要的 。
//        getCurrentSession是所有操作都必须放在事务中进行，并且提交事务后，
//        session就自动关闭，不能够再进行关闭。
          
        //N+1
//        nQry(s);
        
        //查询总数
//		qryTotal(s);
        
        //乐观锁
/*        场景：故意创造一个场景来制造脏数据。
            1. 通过session1得到id=1的对象 product1
            2. 在product1原来价格的基础上增加1000
            3. 更新product1之前，通过session2得到id=1的对象product2
            4. 在product2原来价格的基础上增加1000
            5. 更新product1
            6. 更新product2
                          最后结果是product的价格只增加了1000，而不是2000
                      解决办法：
             1.增加一个version字段，用于版本信息控制。这就是乐观锁的核心机制。
               <version name="version" column="ver" type="int"></version>
                                注意： version元素必须紧跟着id后面，否则会出错。
             2.修改 Product.java  增加version属性
*/
//        happyLock(sf, s);
        
        //c3p0连接池
/*      建立数据库连接时比较消耗时间的，所以通常都会采用数据库连接池的技术来建立多条数据库连接，
                 并且在将来持续使用，从而节约掉建立数据库连接的时间 
        hibernate本身是提供了数据库连接池的，
                 但是hibernate官网也不推荐使用他自带的数据库连接池。 
                 一般都会使用第三方的数据库连接池 
        C3P0是免费的第三方的数据库连接池，并且有不错的表现 
                  注：当运行次数不大的时候，从运行效果上来看，是看不出区别的。 只有在高并发量的情况下，
                  才会体会出来。本知识主要是提供这个相关配置办法，以供以后有需要的时候，查询与修改方便。
        */
        connectionPool(s);
        
        s.getTransaction().commit();
        //Session关闭了与Session失去了联系，相当于脱离了管理，状态就是脱管的
        s.close();
        sf.close();
	}

	/** 
	 * @Title: connectionPool 
	 * @Description: 连接池
	 * 1.hibernate.cfg.xml配置连接池，去掉二级缓存配置
	 * 2.映射xml文件去掉二级缓存配置<cache usage="read-only" />
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
	 * @Description: 乐观锁
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
        System.out.println("产品原本价格是: " + p1.getPrice());
        p1.setPrice(p1.getPrice() + 1000);
        Product p2 = (Product) s2.get(Product.class, 1);
        p2.setPrice(p2.getPrice() + 1000);
        s.update(p1);
        s2.update(p2);
        s2.getTransaction().commit();
        Product p = (Product) s.get(Product.class, 1);
        System.out.println("经过两次价格增加后，价格变为: " + p.getPrice());
        s2.close();
	}

	/** 
	 * @Title: qryTotal 
	 * @Description: 查询总数
	 * select count(*) from ....创建一个Query对象，
	 * 调用Query对象的uniqueResult()方法，返回一个long型的数据，即查询总数
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
	 * Hibernate有缓存机制，可以通过用id作为key把product对象保存在缓存中 
     * 同时hibernate也提供Query的查询方式。假设数据库中有100条记录，
     * 其中有30条记录在缓存中，但是使用Query的的list方法，
     * 就会所有的100条数据都从数据库中查询，而无视这30条缓存中的记录 
     * N+1是什么意思呢，首先执行一条sql语句，去查询这100条记录，但是，
     * 只返回这100条记录的ID 
     * 然后再根据id,进行进一步查询。 
     * 如果id在缓存中，就从缓存中获取product对象了，否则再从数据库中获取.
     * 首先通过Query的iterator把所有满足条件的Product的id查出来
     * 然后再通过it.next()查询每一个对象
     * 如果这个对象在缓存中，就直接从缓存中取了
     * 否则就从数据库中获取
     * N+1中的1，就是指只返回id的SQL语句，N指的是如果在缓存中找不到对应的数据，
     * 就到数据库中去查
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
	 * load延迟加载属性被访问才调用SQL，get非延迟加载立无论后面是否访问属性立马调用sql
     * 当id不存在，load加载方式会抛出异常，get加载方式会返回null
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
	 * @Description: 分页查询
	 * @param @param s
	 * @param @throws HibernateException 
	 * @return void 
	 * @throws 
	 */
	public static void pageQry(Session s) throws HibernateException {
		String name = "iphone";
        Criteria c = s.createCriteria(Product.class);
        c.add(Restrictions.like("name", "%"+name+"%"));
        //表示从第2条数据开始,基0
        c.setFirstResult(0);
        //表示一共查询5条数据
        c.setMaxResults(5);
        
        List<Product> ps = c.list();
        for (Product p : ps) {
            System.out.println(p.getName());
        }
	}

	/** 
	 * @Title: twoLevelCache 
	 * @Description: 二级缓存,二级缓存是在SessionFactory上,需要在xml上配置,
	 * hibernate本身不提供二级缓存，都是使用第三方的二级缓存插件
	 * 这里使用的是 EhCache提供的二级缓存
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
        
        //注意：这是第二个session
        Session s2 = sf.openSession();
        s2.beginTransaction();
        Category p3 = (Category) s2.get(Category.class, 2);
        s2.getTransaction().commit();
        s2.close();
	}

	/** 
	 * @Title: oneLevelCache 
	 * @Description: 一级缓存，默认开启，一级缓存存放在session上
	 * @param @param s
	 * @param @throws HibernateException 
	 * @return void 
	 * @throws 
	 */
	public static void oneLevelCache(Session s) 
			throws HibernateException {
		System.out.println("log1");
        //第一次查询有SQL语句，入库查询
        Category c1 = (Category)s.get(Category.class, 1);
        System.out.println("log2");
        //第二次查询无SQL语句，再session中获取
        Category c2= (Category)s.get(Category.class, 1);
        System.out.println("log3");
	}

	/** 
	 * @Title: deleteCascade 
	 * @Description: 删除级联
	 * cascade="delete"级联，
     *  all：所有操作都执行级联操作（就是 delete+save-update）；
     *  none：所有操作都不执行级联操作（默认）；
     *  delete：删除时执行级联操作(一对多)； 
     *  save-update：保存和更新时执行级联操作（一对多）；
     *  级联通常用在one-many和many-to-many上，几乎不用在many-one上
	 * @param @param s
	 * @param @throws HibernateException 
	 * @return void 
	 * @throws 
	 */
	public static void deleteCascade(Session s) 
			throws HibernateException {
		//删除级联
        Category c = (Category) s.get(Category.class, 2);
        s.delete(c);
	}

	/** 
	 * @Title: relationLazyload 
	 * @Description: 关系延迟加载，在one-many many-many的时候都可以使用关系的延迟加载
	 * 先查询category，等需要product时候再查数据库获得product数据,xml映射文件lazy=“true”
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
	 * @Description: 属性的延迟加载，session.load(Product.class,1);
	 * 在打印log1之前，是不会打印出sql语句的，只有在访问访问属性“getName()"的时候，
	 * 才会访问数据库
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
	 * @Description: 事务，第一个，删除id=1的产品，这个是会成功的
     *  第二个，修改id=2的产品，使得其产品名称超过了数据库中设置的长度30，这个是会失败的。
     *  因为这两个操作都是在一个事务中，而且第二个操作失败了，所以最后的结果是两个操作都没有生效
     *  “注意”：在Mysql中，只有当表的类型是INNODB的时候，才支持事务
     *  修改表的类型为INNODB的SQL：alter table product_ ENGINE  = innodb;
     *  查看表的类型的SQL：show table status from test; 
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
        p2.setName("修改id=2的产品，使得其产品名称超过了数据库中设置的长度30，"
        		+ "这个是会失败的。");
        s.update(p2);
	}

	/** 
	 * @Title: manyToMany 
	 * @Description: 多对多关系
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
	 * @Description: 一对多关系
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
	 * @Description: 多对一关系
	 * 通过多对一关系，设置了id=8的产品对应 id=1的分类
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
        	//new 了一个Product();，在数据库中还没有对应的记录，
			//这个时候Product对象的状态是瞬时的
        	Product p = new Product();
        	p.setName("iphone7"+i);
        	p.setPrice(i);
        	
        	//通过Session的save把该对象保存在了数据库中，
        	//该对象也和Session之间产生了联系，此时状态是持久的。
        	s.save(p);
        }
        
//        Category c = new Category();
//        c.setName("分类1");
//        s.save(c);
	}

}

package com.ljj.pojo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @ClassName: Product 
 * @Description: 为了测试注解配置注释了一些属性
 * 类注解说明：@Entity 表示这是一个实体类，用于映射表（类注解）
 * 类注解说明：@Table(name = "product_") 表示这是一个类，映射到的表名:product_（类注解）

 * 属性注解说明：属性注解是配置在属性对应的getter方法上的
 * 属性注解说明：@Id 表示这是主键
 * 属性注解说明：@GeneratedValue(strategy = GenerationType.IDENTITY) 表示自增长方式使用mysql自带的
 * 属性注解说明：@Column(name = "id") 表示映射到字段id
 * 
 * 属性注解说明：@Column —— 注解声明了属性到列的映射。该注解有如下的属性 
				name 可选，列名（默认值是属性名） 
				unique 可选，是否在该列上设置唯一约束（默认值false） 
				nullable 可选，是否设置该列的值可以为空（默认值false） 
				insertable 可选，该列是否作为生成的insert语句中的一个列（默认值true） 
				updatable 可选，该列是否作为生成的update语句中的一个列（默认值true） 
				columnDefinition 可选，为这个特定列覆盖sql ddl片段（这可能导致无法在不同数据库间移植） 
				table 可选，定义对应的表（默认为主表） 
				length 可选，列长度（默认值255） 
				precision 可选，列十进制精度（decimal precision)(默认值0） 
				scale 可选，如果列十进制数值范围（decimal scale）可用，在此设置（默认值0）
 
 * 属性注解说明：@GeneratedValue —— 注解声明了主键的生成策略。该注解有如下属性 
				strategy 指定生成的策略（JPA定义的），这是一个GenerationType。默认是GenerationType. AUTO 
				GenerationType.AUTO 主键由程序控制 
				GenerationType.TABLE 使用一个特定的数据库表格来保存主键 
				GenerationType.IDENTITY 主键由数据库自动生成（主要是自动增长类型） 
				GenerationType.SEQUENCE 根据底层数据库的序列来生成主键，条件是数据库支持序列。（这个值要与generator一起使用） 
				generator 指定生成主键使用的生成器（可能是orcale中的序列）。 
 
 * 属性注解说明：@SequenceGenerator —— 注解声明了一个数据库序列。该注解有如下属性 
				name 表示该表主键生成策略名称，它被引用在@GeneratedValue中设置的“gernerator”值中 
				sequenceName 表示生成策略用到的数据库序列名称。 
				initialValue 表示主键初始值，默认为0. 
				allocationSize 每次主键值增加的大小，例如设置成1，则表示每次创建新记录后自动加1，默认为50.
 
 * 关系相关注解说明：@ManyToOne 设置多对一关联 
				方法一 
				@ManyToOne(cascade={CasCadeType.PERSIST,CascadeType.MERGE}) 
				@JoinColumn(name="外键") 
				public 主表类 get主表类(){return 主表对象} 
				方法二 
				@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE}) 
				@JoinTable(name="关联表名"， 
				joinColumns = @JoinColumn(name="主表外键"), 
				inverseJoinColumns = @JoinColumns(name="从表外键") 
				) 
 
 * 关系相关注解说明：@OneToMany 设置一对多关联。
				方法一 。 
				“一端”配置 
				@OneToMany(mappedBy="“多端”的属性") 
				public List<“多端”类> get“多端”列表(){return “多端”列表} 
				“多端”配置参考@ManyToOne. 
				方法二 
				“一端”配置 
				@OneToMany(mappedBy="“多端”的属性") 
				@MapKey(name="“多端”做为Key的属性") 
				public Map<“多端”做为Key的属性的类,主表类> get“多端”列表（）{return “多端”列表} 
				“多端”配置参考@ManyToOne. 
				方法三 使用这种配置，在为“一端”添加“多端”时，可以修改“多端”的外键。 
				“一端”配置 
				@OneToMany 
				@JoinColumn(name="“多端”外键") 
				public List<“多端”类> get“多端”列表(){return “多端”列表} 
				“多端”配置参考@ManyToOne.
 
 * @author 刘佳佳 
 * @date 2017年10月11日 下午8:23:40
 */
@Entity
@Table(name="Product_")
public class Product {
    int id;
    String name;
    float price;
    Category category;
    Set<User> users;
//    int version;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    @Column(name="name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Column(name="price")
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    @ManyToOne
    @JoinColumn(name="cid")
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
    @ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinTable(
            name="user_product",
            joinColumns=@JoinColumn(name="pid"),
            inverseJoinColumns=@JoinColumn(name="uid")
    )  
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
//	public int getVersion() {
//		return version;
//	}
//	public void setVersion(int version) {
//		this.version = version;
//	}
    
}

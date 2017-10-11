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
 * @Description: Ϊ�˲���ע������ע����һЩ����
 * ��ע��˵����@Entity ��ʾ����һ��ʵ���࣬����ӳ�����ע�⣩
 * ��ע��˵����@Table(name = "product_") ��ʾ����һ���࣬ӳ�䵽�ı���:product_����ע�⣩

 * ����ע��˵��������ע�������������Զ�Ӧ��getter�����ϵ�
 * ����ע��˵����@Id ��ʾ��������
 * ����ע��˵����@GeneratedValue(strategy = GenerationType.IDENTITY) ��ʾ��������ʽʹ��mysql�Դ���
 * ����ע��˵����@Column(name = "id") ��ʾӳ�䵽�ֶ�id
 * 
 * ����ע��˵����@Column ���� ע�����������Ե��е�ӳ�䡣��ע�������µ����� 
				name ��ѡ��������Ĭ��ֵ���������� 
				unique ��ѡ���Ƿ��ڸ���������ΨһԼ����Ĭ��ֵfalse�� 
				nullable ��ѡ���Ƿ����ø��е�ֵ����Ϊ�գ�Ĭ��ֵfalse�� 
				insertable ��ѡ�������Ƿ���Ϊ���ɵ�insert����е�һ���У�Ĭ��ֵtrue�� 
				updatable ��ѡ�������Ƿ���Ϊ���ɵ�update����е�һ���У�Ĭ��ֵtrue�� 
				columnDefinition ��ѡ��Ϊ����ض��и���sql ddlƬ�Σ�����ܵ����޷��ڲ�ͬ���ݿ����ֲ�� 
				table ��ѡ�������Ӧ�ı�Ĭ��Ϊ���� 
				length ��ѡ���г��ȣ�Ĭ��ֵ255�� 
				precision ��ѡ����ʮ���ƾ��ȣ�decimal precision)(Ĭ��ֵ0�� 
				scale ��ѡ�������ʮ������ֵ��Χ��decimal scale�����ã��ڴ����ã�Ĭ��ֵ0��
 
 * ����ע��˵����@GeneratedValue ���� ע�����������������ɲ��ԡ���ע������������ 
				strategy ָ�����ɵĲ��ԣ�JPA����ģ�������һ��GenerationType��Ĭ����GenerationType. AUTO 
				GenerationType.AUTO �����ɳ������ 
				GenerationType.TABLE ʹ��һ���ض������ݿ������������� 
				GenerationType.IDENTITY ���������ݿ��Զ����ɣ���Ҫ���Զ��������ͣ� 
				GenerationType.SEQUENCE ���ݵײ����ݿ���������������������������ݿ�֧�����С������ֵҪ��generatorһ��ʹ�ã� 
				generator ָ����������ʹ�õ���������������orcale�е����У��� 
 
 * ����ע��˵����@SequenceGenerator ���� ע��������һ�����ݿ����С���ע������������ 
				name ��ʾ�ñ��������ɲ������ƣ�����������@GeneratedValue�����õġ�gernerator��ֵ�� 
				sequenceName ��ʾ���ɲ����õ������ݿ��������ơ� 
				initialValue ��ʾ������ʼֵ��Ĭ��Ϊ0. 
				allocationSize ÿ������ֵ���ӵĴ�С���������ó�1�����ʾÿ�δ����¼�¼���Զ���1��Ĭ��Ϊ50.
 
 * ��ϵ���ע��˵����@ManyToOne ���ö��һ���� 
				����һ 
				@ManyToOne(cascade={CasCadeType.PERSIST,CascadeType.MERGE}) 
				@JoinColumn(name="���") 
				public ������ get������(){return �������} 
				������ 
				@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE}) 
				@JoinTable(name="��������"�� 
				joinColumns = @JoinColumn(name="�������"), 
				inverseJoinColumns = @JoinColumns(name="�ӱ����") 
				) 
 
 * ��ϵ���ע��˵����@OneToMany ����һ�Զ������
				����һ �� 
				��һ�ˡ����� 
				@OneToMany(mappedBy="����ˡ�������") 
				public List<����ˡ���> get����ˡ��б�(){return ����ˡ��б�} 
				����ˡ����òο�@ManyToOne. 
				������ 
				��һ�ˡ����� 
				@OneToMany(mappedBy="����ˡ�������") 
				@MapKey(name="����ˡ���ΪKey������") 
				public Map<����ˡ���ΪKey�����Ե���,������> get����ˡ��б���{return ����ˡ��б�} 
				����ˡ����òο�@ManyToOne. 
				������ ʹ���������ã���Ϊ��һ�ˡ���ӡ���ˡ�ʱ�������޸ġ���ˡ�������� 
				��һ�ˡ����� 
				@OneToMany 
				@JoinColumn(name="����ˡ����") 
				public List<����ˡ���> get����ˡ��б�(){return ����ˡ��б�} 
				����ˡ����òο�@ManyToOne.
 
 * @author ���Ѽ� 
 * @date 2017��10��11�� ����8:23:40
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

package com.levin.oak.base.entities;

import com.levin.commons.dao.annotation.Contains;
import com.levin.commons.dao.domain.MultiTenantObject;
import com.levin.commons.dao.domain.StatefulObject;
import com.levin.commons.dao.domain.support.AbstractBaseEntityObject;
import com.levin.commons.dao.domain.support.AbstractNamedEntityObject;
import com.levin.commons.dao.domain.support.AbstractTreeObject;
import com.levin.commons.service.domain.EnumDesc;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;


@Entity(name = EntityConst.PREFIX + "Org")
@Data
@EqualsAndHashCode(of = {"id"})
@Accessors(chain = true)
@FieldNameConstants
@Schema(title = "机构")
@Table(
        indexes = {
                @Index(columnList = AbstractBaseEntityObject.Fields.orderCode),
                @Index(columnList = AbstractBaseEntityObject.Fields.enable),
                @Index(columnList = AbstractBaseEntityObject.Fields.createTime),
                @Index(columnList = AbstractBaseEntityObject.Fields.creator),

                @Index(columnList = AbstractNamedEntityObject.Fields.name),
//                @Index(columnList = AbstractTreeObject.Fields.parentId),
//                @Index(columnList = AbstractTreeObject.Fields.idPath),

                @Index(columnList = E_Org.parentId),
                @Index(columnList = E_Org.code),
                @Index(columnList = E_Org.areaCode),
                @Index(columnList = E_Org.tenantId),
                @Index(columnList = E_Org.type),
                @Index(columnList = E_Org.category),
                @Index(columnList = E_Org.state),
                @Index(columnList = E_Org.level),
        }
        ,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {E_Org.tenantId, E_Org.parentId, E_Org.name}),
                @UniqueConstraint(columnNames = {E_Org.tenantId, E_Org.parentId, E_Org.code}),
        }
)

//关于 JPA 继承模型
//@DiscriminatorColumn
//@DiscriminatorValue
//@PrimaryKeyJoinColumn(name="aId", referencedColumnName="id")
//@Inheritance( strategy = InheritanceType.TABLE_PER_CLASS )
//@MappedSuperclass告诉JPA提供者包含基类的持久性属性，就好像它们是由扩展用@MappedSuperclass注解的超类的子类所声明的@MappedSuperclass 。
//但是，inheritance仅在OOP世界中是可见的，因为从数据库的angular度来看，没有任何基类的迹象。 只有子类实体将有一个关联的映射表。
//@Inheritance注释是为了实现数据库表结构中的OOP Inheritance模型。 更多的，你可以查询用@Inheritance注解的基类，但是你不能用@MappedSuperclass注解的基类。
//现在，您要使用@Inheritance JPA注释的原因是要实施像“战略模式”这样的行为驱动模式 。另一方面， @MappedSuperclass只是一种重用基本属性，关联，甚至是使用公共基类的实体@Id方法。
//不过，使用@Embeddabletypes可以达到几乎相同的目标。 唯一的区别是你不能重复@Embeddable的@Id定义，但你可以用@MappedSuperclass 。

//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Org
        extends AbstractTreeObject<String, Org>
        implements MultiTenantObject, StatefulObject {

    @Schema(title = "状态")
    public enum State implements EnumDesc {
        @Schema(title = "正常")
        Normal,
        @Schema(title = "冻结")
        Freeze,
        @Schema(title = "注销")
        Cancellation,
    }

    @Schema(title = "组织类型")
    public enum Type implements EnumDesc {
        @Schema(title = "公司/独立法人")
        LegalPerson,
        @Schema(title = "个体户")
        Individual,
        @Schema(title = "分公司/分支机构")
        Branch,
        @Schema(title = "部门")
        Department,
        @Schema(title = "小组")
        Group,
        @Schema(title = "其它")
        Other,
    }

    @Id
    @GeneratedValue(generator = "default_id")
    @Column(length = 64)
    protected String id;

    @Schema(title = "租户ID")
    @Column(length = 64)
    protected String tenantId;

    @Schema(title = "父ID")
    @Column(length = 64)
    protected String parentId;

    @Schema(title = "编码", description = "对于公司是统一信用码")
    @Column(length = 64)
    @Contains
    protected String code;

    @Schema(title = "图标")
    protected String icon;

    @Schema(title = "状态")
    @Column(nullable = false, length = 32)
    @Enumerated(EnumType.STRING)
    protected State state;

    @Schema(title = "类型")
    @Column(nullable = false, length = 64)
    @Enumerated(EnumType.STRING)
    protected Type type;

    @Schema(title = "所属行业")
    @Column(length = 64)
    protected String industries;

    @Schema(title = "区域编码")
    @Column(nullable = false, length = 64)
    @Contains
    protected String areaCode;

    @Schema(title = "所属区域")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "areaCode", insertable = false, updatable = false)
    protected Area area;

    @Schema(title = "机构级别", description = "使用字典值配置")
    @Column(length = 128)
    protected String level;

    @Column(nullable = false, length = 128)
    @Schema(title = "机构类别", description = "使用字典值配置")
    protected String category;

    @Column(nullable = false)
    @Schema(title = "是否外部机构")
    protected Boolean isExternal;

    //////////////////////////////////////////////////////////////////////

    @Schema(title = "联系人")
    @Column(length = 64)
    @Contains
    protected String contacts;

    @Schema(title = "联系电话")
    @Column(length = 20)
    @Contains
    protected String phones;

    @Schema(title = "联系邮箱")
    @Column(length = 32)
    protected String emails;

    @Schema(title = "联系地址")
    @Contains
    protected String address;

    @Schema(title = "邮政编码")
    @Column(length = 32)
    protected String zipCode;

    @Lob
    @Basic(fetch = FetchType.LAZY) //默认延迟加载
    @Schema(title = "机构扩展信息")
    protected String extInfo;

    @Override
    @PrePersist
    public void prePersist() {

        super.prePersist();

        if (state == null) {
            state = State.Normal;
        }
    }

}

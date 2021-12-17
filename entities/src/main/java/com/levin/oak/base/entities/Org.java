package com.levin.oak.base.entities;

import com.levin.commons.dao.domain.MultiTenantObject;
import com.levin.commons.dao.domain.StatefulObject;
import com.levin.commons.dao.domain.support.AbstractBaseEntityObject;
import com.levin.commons.dao.domain.support.AbstractNamedEntityObject;
import com.levin.commons.dao.domain.support.AbstractTreeObject;
import com.levin.commons.service.domain.InjectVar;
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
@Schema(description = "机构")
@Table(
        indexes = {
                @Index(columnList = AbstractBaseEntityObject.Fields.orderCode),
                @Index(columnList = AbstractBaseEntityObject.Fields.enable),
                @Index(columnList = AbstractBaseEntityObject.Fields.createTime),
                @Index(columnList = AbstractBaseEntityObject.Fields.creator),

                @Index(columnList = AbstractNamedEntityObject.Fields.name),
                @Index(columnList = AbstractTreeObject.Fields.parentId),
//                @Index(columnList = AbstractTreeObject.Fields.idPath),

                @Index(columnList = E_Org.code),
                @Index(columnList = E_Org.areaCode),
                @Index(columnList = E_Org.tenantId),
                @Index(columnList = E_Org.type),
                @Index(columnList = E_Org.category),
                @Index(columnList = E_Org.state),
                @Index(columnList = E_Org.level),
        }
//        ,
//        uniqueConstraints = {
//                @UniqueConstraint(columnNames = {E_Org.tenantId, E_Org.code}),
//                @UniqueConstraint(columnNames = {E_Org.tenantId, E_Org.name}),
//        }
)

//关于 JPA 继承模型
//@DiscriminatorColumn
//@DiscriminatorValue
//@PrimaryKeyJoinColumn(name="aId", referencedColumnName="id")
//@Inheritance( strategy = InheritanceType.TABLE_PER_CLASS )
//@MappedSuperclass告诉JPA提供者包含基类的持久性属性，就好像它们是由扩展用@MappedSuperclass注解的超类的子类所声明的@MappedSuperclass 。
//但是，inheritance仅在OOP世界中是可见的，因为从数据库的angular度来看，没有任何基类的迹象。 只有子类实体将有一个关联的映射表。
//@Inheritance注释是为了实现数据库表结构中的OOPinheritance模型。 更多的，你可以查询用@Inheritance注解的基类，但是你不能用@MappedSuperclass注解的基类。
//现在，您要使用@Inheritance JPA注释的原因是要实施像“战略模式”这样的行为驱动模式 。另一方面， @MappedSuperclass只是一种重用基本属性，关联，甚至是使用公共基类的实体@Id方法。
//不过，使用@Embeddabletypes可以达到几乎相同的目标。 唯一的区别是你不能重复@Embeddable的@Id定义，但你可以用@MappedSuperclass 。

//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Org
        extends AbstractTreeObject<Long, Org>
        implements MultiTenantObject, StatefulObject {

    public enum State {
        @Schema(description = "正常")
        Normal,
        @Schema(description = "冻结")
        Freeze,
        @Schema(description = "注销")
        Cancellation,
    }

    public enum Type {
        @Schema(description = "公司/独立法人")
        LegalPerson,
        @Schema(description = "分公司/分支机构")
        Branch,
        @Schema(description = "部门")
        Department,
        @Schema(description = "小组")
        Group,
        @Schema(description = "其它")
        Other,
    }

    @Id
    @GeneratedValue
    protected Long id;

    @Schema(description = "租户ID")
    @InjectVar
    protected String tenantId;

    @Schema(description = "编码", title = "对于公司是统一信用码")
    @Column(length = 128)
    protected String code;

    @Schema(description = "图标")
    protected String icon;

    @Schema(description = "状态")
    @Column(nullable = false)
    protected State state;

    @Schema(description = "类型")
    @Column(nullable = false)
    protected Type type;

    @Schema(description = "所属行业")
    @Column(length = 64)
    protected String industries;

    @Schema(description = "区域编码")
    @Column(nullable = false, length = 64)
    protected String areaCode;

    @Schema(description = "所属区域")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "areaCode", insertable = false, updatable = false)
    protected Area area;

    @Schema(description = "机构级别", title = "使用字典值配置")
    @Column(length = 128)
    protected String level;

    @Column(nullable = false, length = 128)
    @Schema(description = "机构类别", title = "使用字典值配置")
    protected String category;

    @Column(nullable = false)
    @Schema(description = "是否外部机构")
    protected Boolean isExternal;

    //////////////////////////////////////////////////////////////////////

    @Schema(description = "联系人")
    @Column(length = 64)
    protected String contacts;

    @Schema(description = "联系电话")
    @Column(length = 20)
    protected String phones;

    @Schema(description = "联系邮箱")
    @Column(length = 32)
    protected String emails;

    @Schema(description = "联系地址")
    protected String address;

    @Schema(description = "邮政编码")
    @Column(length = 32)
    protected String zipCode;

    @Override
    public void prePersist() {

        super.prePersist();

        if (state == null) {
            state = State.Normal;
        }

    }

}

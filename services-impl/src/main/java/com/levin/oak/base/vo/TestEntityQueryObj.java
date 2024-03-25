package com.levin.oak.base.vo;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.Ignore;
import com.levin.commons.dao.domain.support.TestEntity;
import com.levin.oak.base.services.commons.req.MultiTenantOrgReq;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TargetOption(entityClass = TestEntity.class)
public class TestEntityQueryObj extends MultiTenantOrgReq<TestEntityQueryObj> {

    @Ignore
    boolean containsPublicData;

    @Ignore
    boolean tenantShared;

    @Ignore
    boolean containsOrgPublicData;

    @Ignore
    boolean orgShared;

}

package com.levin.oak.base.services.menures;

import static com.levin.oak.base.ModuleOption.*;

import com.levin.commons.dao.*;
import com.levin.commons.dao.support.*;
import com.levin.commons.service.domain.*;

import java.util.*;
import java.util.stream.*;
import org.springframework.transaction.annotation.*;
import org.springframework.util.*;
import org.springframework.beans.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.swagger.v3.oas.annotations.*;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.MenuRes;

import com.levin.oak.base.services.menures.req.*;
import com.levin.oak.base.services.menures.info.*;


////////////////////////////////////
//自动导入列表
    import com.levin.commons.plugin.MenuItem.*;
    import com.levin.oak.base.entities.ResOperation;
    import java.util.List;
    import com.levin.oak.base.entities.MenuRes;
    import com.levin.oak.base.services.menures.info.*;
    import java.util.Set;
    import java.util.Date;
////////////////////////////////////

/**
 *  菜单服务实现
 *
 *@author auto gen by simple-dao-codegen 2021-10-28 16:17:42
 *
 */

//@Valid只能用在controller。@Validated可以用在其他被spring管理的类上。

@Service(PLUGIN_PREFIX + "MenuResService")
@Slf4j
//@Validated
public class MenuResServiceImpl implements MenuResService {

    @Autowired
    private SimpleDao simpleDao;

    @Operation(tags = {ENTITY_NAME}, summary = "新增" + ENTITY_NAME)
    @Override
    public Long create(CreateMenuResReq req){
        MenuRes entity = simpleDao.create(req);
        return entity.getId();
    }

    @Operation(tags = {ENTITY_NAME}, summary = "批量新增" + ENTITY_NAME)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<Long> batchCreate(List<CreateMenuResReq> reqList){
        return reqList.stream().map(this::create).collect(Collectors.toList());
    }

    @Operation(tags = {ENTITY_NAME}, summary = "通过ID找回" + ENTITY_NAME)
    @Override
    public MenuResInfo findById(Long id) {
        return simpleDao.findOneByQueryObj(new QueryMenuResReq().setId(id));
    }

    @Operation(tags = {ENTITY_NAME}, summary = "更新" + ENTITY_NAME)
    @Override
    public int update(UpdateMenuResReq req) {
        return simpleDao.updateByQueryObj(req);
    }

    @Operation(tags = {ENTITY_NAME}, summary = "批量更新" + ENTITY_NAME)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<Integer> batchUpdate(List<UpdateMenuResReq> reqList){
        return reqList.stream().map(this::update).collect(Collectors.toList());
    }

    @Operation(tags = {ENTITY_NAME}, summary = "删除" + ENTITY_NAME)
    @Override
    public int delete(DeleteMenuResReq req) {
        return simpleDao.deleteByQueryObj(req);
    }

    @Operation(tags = {ENTITY_NAME}, summary = "分页查找" + ENTITY_NAME)
    @Override
    public PagingData<MenuResInfo> query(QueryMenuResReq req, Paging paging) {
        return simpleDao.findPagingDataByQueryObj(req, paging);
    }
}

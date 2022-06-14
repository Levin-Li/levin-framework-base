/*
 * Copyright (c) 2017 Baidu, Inc. All Rights Reserve.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.levin.oak.base.baidu.uid.worker.dao;

import com.levin.oak.base.baidu.uid.ModuleOption;
import com.levin.oak.base.baidu.uid.worker.entity.WorkerNodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO for M_WORKER_NODE
 *
 * @author yutianbao
 */
@Repository(ModuleOption.PLUGIN_PREFIX + "WorkerNodeDAO")
public interface WorkerNodeDAO extends JpaRepository<Long, WorkerNodeEntity> {

    /**
     * Get {@link WorkerNodeEntity} by node host
     *
     * @param host
     * @param port
     * @return
     */
    WorkerNodeEntity getWorkerNodeByHostPort(String host, String port);

    /**
     * Add {@link WorkerNodeEntity}
     *
     * @param workerNodeEntity
     */
    void addWorkerNode(WorkerNodeEntity workerNodeEntity);

}

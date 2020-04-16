/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.zookeeper.server.quorum.flexible;

import java.util.Set;

//import org.apache.zookeeper.server.quorum.QuorumCnxManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class implements a validator for majority quorums. The 
 * implementation is straightforward.
 *
 */
public class QuorumMaj implements QuorumVerifier {
    private static final Logger LOG = LoggerFactory.getLogger(QuorumMaj.class);
    
    int half;
    
    /**
     * Defines a majority to avoid computing it every time.
     * 
     * @param n number of servers
     */
    public QuorumMaj(int n){
        this.half = n/2;
    }
    
    /**
     * Returns weight of 1 by default.
     * 
     * @param id 
     */
    public long getWeight(long id){
        return (long) 1;
    }
    
    /**
     * Verifies if a set is a majority.
     */
    //判断当前节点的票数是否是大于一半，默认采用 QuorumMaj 来实现
    public boolean containsQuorum(Set<Long> set){
        //这个 half 的值是多少呢？，也就是说，在构建 QuorumMaj 的时候，传递了当前集群节点的数量，这里是 3
        // 那么， hafl=3/2=1 可以在 QuorumPeerConfig.parseProperties 这个方法中，找到如下代码。

        //那么 set.size()>1. 意味着至少要有两个节点的票据是选择你当 leader，否则，还得继续投
        return (set.size() > half); //已经归纳的票据是否大于half .2>1  -> leader选举、 数据同步
    }
    
}

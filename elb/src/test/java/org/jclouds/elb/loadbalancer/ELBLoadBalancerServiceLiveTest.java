/**
 * Licensed to jclouds, Inc. (jclouds) under one or more
 * contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  jclouds licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jclouds.elb.loadbalancer;

import static org.testng.Assert.assertEquals;

import org.jclouds.collect.PaginatedSet;
import org.jclouds.compute.domain.NodeMetadata;
import org.jclouds.elb.ELBAsyncClient;
import org.jclouds.elb.ELBClient;
import org.jclouds.elb.domain.LoadBalancer;
import org.jclouds.loadbalancer.BaseLoadBalancerServiceLiveTest;
import org.jclouds.rest.RestContext;
import org.jclouds.sshj.config.SshjSshClientModule;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;

/**
 * 
 * @author Lili Nadar
 */
@Test(groups = "live", singleThreaded = true, testName = "ELBLoadBalancerServiceLiveTest")
public class ELBLoadBalancerServiceLiveTest extends BaseLoadBalancerServiceLiveTest {
   public ELBLoadBalancerServiceLiveTest() {
      provider = "elb";
   }

   @Override
   protected SshjSshClientModule getSshModule() {
      return new SshjSshClientModule();
   }

   @Override
   protected void validateNodesInLoadBalancer() {
      RestContext<ELBClient, ELBAsyncClient> elbContext = view.unwrap();
      // TODO create a LoadBalancer object and an appropriate list method so that this
      // does not have to be EC2 specific code
      ELBClient elbClient = elbContext.getApi();

      Builder<String> instanceIds = ImmutableSet.<String> builder();
      for (NodeMetadata node : nodes) {
         instanceIds.add(node.getProviderId());
      }

      PaginatedSet<LoadBalancer> elbs = elbClient.getLoadBalancerClientForRegion(null).list();
      for (LoadBalancer elb : elbs) {
         if (elb.getName().equals(group))
            assertEquals(elb.getInstanceIds(), instanceIds.build());
      }
   }

}

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jclouds.abiquo.compute.strategy;

import org.jclouds.abiquo.compute.strategy.internal.FindCompatibleVirtualDatacentersForImageAndConversions;
import org.jclouds.abiquo.domain.cloud.VirtualDatacenter;
import org.jclouds.abiquo.domain.cloud.VirtualMachineTemplate;

import com.google.inject.ImplementedBy;

/**
 * Finds all virtual datacenters where the given {@link VirtualMachineTemplate}
 * can be deployed.
 * 
 * @author Ignasi Barrera
 */
@ImplementedBy(FindCompatibleVirtualDatacentersForImageAndConversions.class)
public interface FindCompatibleVirtualDatacenters {
   Iterable<VirtualDatacenter> execute(VirtualMachineTemplate template);
}
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

package org.apache.shardingsphere.infra.yaml.config.swapper.rule;

import org.apache.shardingsphere.infra.config.rule.RuleConfiguration;
import org.apache.shardingsphere.infra.util.spi.type.ordered.OrderedSPILoader;
import org.apache.shardingsphere.infra.util.yaml.datanode.YamlDataNode;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

/**
 * TODO Rename YamlGlobalRuleConfigurationSwapperEngine when metadata structure adjustment completed. #25485
 * YAML global rule configuration swapper engine.
 */
public final class NewYamlGlobalRuleConfigurationSwapperEngine {
    
    /**
     * Swap to YAML global rule configurations.
     *
     * @param ruleConfigs rule configurations
     * @return YAML global rule configurations
     */
    @SuppressWarnings("rawtypes")
    public Map<RuleConfiguration, NewYamGlobalRuleConfigurationSwapper> swapToYamlRuleConfigurations(final Collection<RuleConfiguration> ruleConfigs) {
        return OrderedSPILoader.getServices(NewYamGlobalRuleConfigurationSwapper.class, ruleConfigs);
    }
    
    /**
     * Swap from YAML global rule configurations to rule configurations.
     *
     * @param dataNodes YAML data nodes
     * @return global rule configurations
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Collection<RuleConfiguration> swapToRuleConfigurations(final Collection<YamlDataNode> dataNodes) {
        Collection<RuleConfiguration> result = new LinkedList<>();
        for (NewYamGlobalRuleConfigurationSwapper each : OrderedSPILoader.getServices(NewYamGlobalRuleConfigurationSwapper.class)) {
            result.add((RuleConfiguration) each.swapToObject(dataNodes));
        }
        return result;
    }
}

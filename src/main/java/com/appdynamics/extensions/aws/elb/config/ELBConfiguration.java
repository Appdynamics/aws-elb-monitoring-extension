/*
 *   Copyright 2018 . AppDynamics LLC and its affiliates.
 *   All Rights Reserved.
 *   This is unpublished proprietary source code of AppDynamics LLC and its affiliates.
 *   The copyright notice above does not evidence any actual or intended publication of such source code.
 *
 */

package com.appdynamics.extensions.aws.elb.config;

import com.appdynamics.extensions.aws.config.Configuration;

import java.util.List;


public class ELBConfiguration extends Configuration {

    private List<String> includeLoadBalancerName;

    public List<String> getIncludeLoadBalancerName() {
        return includeLoadBalancerName;
    }

    public void setIncludeLoadBalancerName(List<String> includeLoadBalancerName) {
        this.includeLoadBalancerName = includeLoadBalancerName;
    }
}
/*
 *   Copyright 2019 . AppDynamics LLC and its affiliates.
 *   All Rights Reserved.
 *   This is unpublished proprietary source code of AppDynamics LLC and its affiliates.
 *   The copyright notice above does not evidence any actual or intended publication of such source code.
 *
 */

package com.appdynamics.extensions.aws.elb;

import com.appdynamics.extensions.aws.config.Dimension;
import com.appdynamics.extensions.aws.config.IncludeMetric;
import com.appdynamics.extensions.aws.dto.AWSMetric;
import com.appdynamics.extensions.aws.metric.AccountMetricStatistics;
import com.appdynamics.extensions.aws.metric.MetricStatistic;
import com.appdynamics.extensions.aws.metric.NamespaceMetricStatistics;
import com.appdynamics.extensions.aws.metric.RegionMetricStatistics;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ELBMetricsProcessorTest {


    NamespaceMetricStatistics namespaceMetricStatistics = new NamespaceMetricStatistics();

    @Before
    public void init() {

        List<com.amazonaws.services.cloudwatch.model.Dimension> dimensionsList1 = Lists.newArrayList();
        List<com.amazonaws.services.cloudwatch.model.Dimension> dimensionsList2 = Lists.newArrayList();
        List<com.amazonaws.services.cloudwatch.model.Dimension> dimensionsList3 = Lists.newArrayList();
        List<com.amazonaws.services.cloudwatch.model.Dimension> dimensionsList4 = Lists.newArrayList();

        com.amazonaws.services.cloudwatch.model.Dimension dimension1 = new com.amazonaws.services.cloudwatch.model.Dimension();
        dimension1.withName("LoadBalancer").withValue("balancer1");

        com.amazonaws.services.cloudwatch.model.Dimension dimension2 = new com.amazonaws.services.cloudwatch.model.Dimension();
        dimension2.withName("AvailabilityZone").withValue("us-east-1");

        com.amazonaws.services.cloudwatch.model.Dimension dimension3 = new com.amazonaws.services.cloudwatch.model.Dimension();
        dimension3.withName("TargetGroup").withValue("UK");

        com.amazonaws.services.cloudwatch.model.Dimension dimension4 = new com.amazonaws.services.cloudwatch.model.Dimension();
        dimension4.withName("TargetGroup").withValue("US");

        com.amazonaws.services.cloudwatch.model.Dimension dimension5 = new com.amazonaws.services.cloudwatch.model.Dimension();
        dimension5.withName("TargetGroup").withValue("APAC");

        dimensionsList1.add(dimension1); //LoadBalancer, StorageType for Storage Metrics
        dimensionsList1.add(dimension2);


        dimensionsList2.add(dimension1);
        dimensionsList2.add(dimension3);

        dimensionsList3.add(dimension1);
        dimensionsList3.add(dimension4);

        dimensionsList4.add(dimension1);
        dimensionsList4.add(dimension5);

        AccountMetricStatistics accountMetricStatistics = new AccountMetricStatistics();
        accountMetricStatistics.setAccountName("AppD");
        namespaceMetricStatistics.setNamespace("AWS/ELB");
        RegionMetricStatistics regionMetricStatistics = new RegionMetricStatistics();
        regionMetricStatistics.setRegion("us-east-1");

        //-- 1st metric//
        com.amazonaws.services.cloudwatch.model.Metric metric1 = new com.amazonaws.services.cloudwatch.model.Metric();
        metric1.setDimensions(dimensionsList1);

        List includeMetrics1 = Lists.newArrayList();
        IncludeMetric includeMetric1 = new IncludeMetric();
        includeMetric1.setName("testmetric1");
        includeMetrics1.add(includeMetric1);

        AWSMetric awsMetric1 = new AWSMetric();
        awsMetric1.setIncludeMetric(includeMetric1);
        awsMetric1.setMetric(metric1);

        MetricStatistic metricStatistic1 = new MetricStatistic();
        metricStatistic1.setValue(1.0);
        metricStatistic1.setUnit("TestUnits");
        metricStatistic1.setMetricPrefix("Custom Metric|AWS ELB|");
        metricStatistic1.setMetric(awsMetric1);

        //-2nd metric--//
        com.amazonaws.services.cloudwatch.model.Metric metric2 = new com.amazonaws.services.cloudwatch.model.Metric();
        metric2.setDimensions(dimensionsList2);

        List includeMetrics2 = Lists.newArrayList();
        IncludeMetric includeMetric2 = new IncludeMetric();
        includeMetric2.setName("testmetric2");
        includeMetrics2.add(includeMetric2);

        AWSMetric awsMetric2 = new AWSMetric();
        awsMetric2.setIncludeMetric(includeMetric2);
        awsMetric2.setMetric(metric2);

        MetricStatistic metricStatistic2 = new MetricStatistic();
        metricStatistic2.setValue(1.0);
        metricStatistic2.setUnit("TestUnits");
        metricStatistic2.setMetricPrefix("Custom Metric|AWS ELB|");
        metricStatistic2.setMetric(awsMetric2);

        //-- 3rd metric//
        com.amazonaws.services.cloudwatch.model.Metric metric3 = new com.amazonaws.services.cloudwatch.model.Metric();
        metric3.setDimensions(dimensionsList3);

        List includeMetrics3 = Lists.newArrayList();
        IncludeMetric includeMetric3 = new IncludeMetric();
        includeMetric3.setName("testmetric3");
        includeMetrics3.add(includeMetric3);

        AWSMetric awsMetric3 = new AWSMetric();
        awsMetric3.setIncludeMetric(includeMetric3);
        awsMetric3.setMetric(metric3);

        MetricStatistic metricStatistic3 = new MetricStatistic();
        metricStatistic3.setValue(1.0);
        metricStatistic3.setUnit("TestUnits");
        metricStatistic3.setMetricPrefix("Custom Metric|AWS ELB|");
        metricStatistic3.setMetric(awsMetric3);

        //--4th metric-//

        com.amazonaws.services.cloudwatch.model.Metric metric4 = new com.amazonaws.services.cloudwatch.model.Metric();
        metric4.setDimensions(dimensionsList4);

        List includeMetrics4 = Lists.newArrayList();
        IncludeMetric includeMetric4 = new IncludeMetric();
        includeMetric4.setName("testmetric4");
        includeMetrics4.add(includeMetric4);

        AWSMetric awsMetric4 = new AWSMetric();
        awsMetric4.setIncludeMetric(includeMetric4);
        awsMetric4.setMetric(metric4);

        MetricStatistic metricStatistic4 = new MetricStatistic();
        metricStatistic4.setValue(1.0);
        metricStatistic4.setUnit("TestUnits");
        metricStatistic4.setMetricPrefix("Custom Metric|AWS ELB|");
        metricStatistic4.setMetric(awsMetric4);

        //add all metrics to region metrics
        regionMetricStatistics.addMetricStatistic(metricStatistic1);
        regionMetricStatistics.addMetricStatistic(metricStatistic2);
        regionMetricStatistics.addMetricStatistic(metricStatistic3);
        regionMetricStatistics.addMetricStatistic(metricStatistic4);

        accountMetricStatistics.add(regionMetricStatistics);
        namespaceMetricStatistics.add(accountMetricStatistics);

    }

    @Test
    public void whenPrintingMetricThenCheckMetricPath() {

        List<Dimension> dimensionsFromConfig = Lists.newArrayList();
        Dimension dimension1 = new Dimension();
        dimension1.setName("LoadBalancer");
        dimension1.setDisplayName("Load Balancer");
        dimensionsFromConfig.add(dimension1);

        Dimension dimension2 = new Dimension();
        dimension2.setName("AvailabilityZone");
        dimension2.setDisplayName("Availability Zone");
        dimensionsFromConfig.add(dimension2);

        Dimension dimension3 = new Dimension();
        dimension3.setName("TargetGroup");
        dimension3.setDisplayName("Target Group");
        dimensionsFromConfig.add(dimension3);

        ELBMetricsProcessor elbMetricsProcessor = new ELBMetricsProcessor(new ArrayList(), dimensionsFromConfig, "AWS/ELB");

        List<com.appdynamics.extensions.metrics.Metric> stats = elbMetricsProcessor.createMetricStatsMapForUpload(namespaceMetricStatistics);

        Assert.assertNotNull(stats);
        Assert.assertEquals(stats.get(0).getMetricPath(),
                "Custom Metric|AWS ELB|AppD|us-east-1|Load Balancer|balancer1|Availability Zone|us-east-1|testmetric1");
        Assert.assertEquals(stats.get(1).getMetricPath(),
                "Custom Metric|AWS ELB|AppD|us-east-1|Load Balancer|balancer1|Target Group|UK|testmetric2");
        Assert.assertEquals(stats.get(2).getMetricPath(),
                "Custom Metric|AWS ELB|AppD|us-east-1|Load Balancer|balancer1|Target Group|US|testmetric3");
        Assert.assertEquals(stats.get(3).getMetricPath(),
                "Custom Metric|AWS ELB|AppD|us-east-1|Load Balancer|balancer1|Target Group|APAC|testmetric4");

    }

}

/*
 * Copyright 2018. AppDynamics LLC and its affiliates.
 * All Rights Reserved.
 * This is unpublished proprietary source code of AppDynamics LLC and its affiliates.
 * The copyright notice above does not evidence any actual or intended publication of such source code.
 *
 */

package com.appdynamics.extensions.aws.elb;

import static com.appdynamics.extensions.aws.Constants.METRIC_PATH_SEPARATOR;

import com.appdynamics.extensions.aws.SingleNamespaceCloudwatchMonitor;
import com.appdynamics.extensions.aws.collectors.NamespaceMetricStatisticsCollector;
import com.appdynamics.extensions.aws.metric.processors.MetricsProcessor;
import com.appdynamics.extensions.xml.Xml;
import com.singularity.ee.agent.systemagent.api.exception.TaskExecutionException;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import com.appdynamics.extensions.aws.elb.config.ELBConfiguration;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Satish Muddam
 */
public class ELBMonitor extends SingleNamespaceCloudwatchMonitor<ELBConfiguration> {

    private static final Logger LOGGER = Logger.getLogger(ELBMonitor.class);

    private static final String DEFAULT_METRIC_PREFIX = String.format("%s%s%s%s",
            "Custom Metrics", METRIC_PATH_SEPARATOR, "Amazon ELB", METRIC_PATH_SEPARATOR);

    private Map customDashboard;
    private String dashboardXML;
    private Dashboard dashboard;

    public ELBMonitor() {
        super(ELBConfiguration.class);
    }

    @Override
    public String getDefaultMetricPrefix() {
        return DEFAULT_METRIC_PREFIX;
    }

    @Override
    public String getMonitorName() {
        return "ELBMonitor";
    }

    @Override
    protected int getTaskCount() {
        return 3;
    }

    @Override
    protected void initialize(ELBConfiguration config) {
        super.initialize(config);
    }


    @Override
    protected NamespaceMetricStatisticsCollector getNamespaceMetricsCollector(
            ELBConfiguration config) {
        customDashboard = config.getCustomDashboard();
        dashboard = new Dashboard(customDashboard, dashboardXML);

        MetricsProcessor metricsProcessor = createMetricsProcessor(config);


        LOGGER.debug("INTERNAL dashboard object created");

        return new NamespaceMetricStatisticsCollector
                .Builder(config.getAccounts(),
                config.getConcurrencyConfig(),
                config.getMetricsConfig(),
                metricsProcessor,
                config.getMetricPrefix())
                .withCredentialsDecryptionConfig(config.getCredentialsDecryptionConfig())
                .withProxyConfig(config.getProxyConfig())
                .build();
    }

    @Override
    protected void initializeMoreStuff(Map<String, String> args) {
//        getContextConfiguration().setMetricXml(args.get("dashboard-file"), Xml.class);
        LOGGER.debug("INTERNAL reached initializeMoreStuff");
        try {
            dashboardXML = FileUtils.readFileToString(new File(args.get("dashboard-file")));
        } catch (Exception e){
            LOGGER.debug("Unable to get file for dashboard", e);
        }

        LOGGER.debug("INTERNAL reached leaving");

    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }

    private MetricsProcessor createMetricsProcessor(ELBConfiguration config) {
        return new ELBMetricsProcessor(
                config.getMetricsConfig().getIncludeMetrics(),
                config.getDimensions(), dashboard);
    }


    public static void main(String[] args) throws TaskExecutionException {

        ConsoleAppender ca = new ConsoleAppender();
        ca.setWriter(new OutputStreamWriter(System.out));
        ca.setLayout(new PatternLayout("%-5p [%t]: %m%n"));
        ca.setThreshold(Level.DEBUG);
        LOGGER.getRootLogger().addAppender(ca);


        /*FileAppender fa = new FileAppender(new PatternLayout("%-5p [%t]: %m%n"), "cache.log");
        fa.setThreshold(Level.DEBUG);
        LOGGER.getRootLogger().addAppender(fa);*/


        ELBMonitor monitor = new ELBMonitor();


        Map<String, String> taskArgs = new HashMap<String, String>();
//        taskArgs.put("config-file", "/Users/bhuvnesh.kumar/repos/appdynamics/extensions/aws-elb-monitoring-extension/src/main/resources/conf/config.yml");
//        taskArgs.put("dashboard-file", "/Users/bhuvnesh.kumar/repos/appdynamics/extensions/aws-elb-monitoring-extension/src/main/resources/conf/dashboard.xml");


        taskArgs.put("config-file", "//Applications/AppDynamics/ma43/monitors/AWSELBMonitor_dash/config.yml");
        taskArgs.put("dashboard-file", "/Users/bhuvnesh.kumar/repos/appdynamics/extensions/aws-elb-monitoring-extension/src/main/resources/conf/dashboard.json");


        monitor.execute(taskArgs, null);

    }


//    @Override
//    protected void onComplete(){
//        LOGGER.debug("INTERNAL reached onComplete");
//        Dashboard dashboard = new Dashboard(customDashboard, dashboardXML);
//        LOGGER.debug("INTERNAL leaving onComplete");
//    }

}

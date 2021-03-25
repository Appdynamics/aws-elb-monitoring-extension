package com.appdynamics.extensions.aws.config;

import com.appdynamics.extensions.metrics.MetricCharSequenceReplacer;
import com.appdynamics.extensions.controller.ControllerInfo;
import java.util.Map;
import java.util.List;

public class Configuration
{
    private List<Account> accounts;
    private CredentialsDecryptionConfig credentialsDecryptionConfig;
    private ProxyConfig proxyConfig;
    private MetricsConfig metricsConfig;
    private ConcurrencyConfig concurrencyConfig;
    private Map<String, String> regionEndPoints;
    private String metricPrefix;
    private TaskSchedule taskSchedule;
    private String cloudWatchMonitoring;
    private int cloudWatchMonitoringInterval;
    private String namespace;
    private List<Dimension> dimensions;
    private ControllerInfo controllerInfo;
    private Boolean enableHealthChecks;
    private String encryptionKey;
    private MetricCharSequenceReplacer metricCharSequenceReplacer;
    
    public List<Account> getAccounts() {
        return this.accounts;
    }
    
    public void setAccounts(final List<Account> accounts) {
        this.accounts = accounts;
    }
    
    public CredentialsDecryptionConfig getCredentialsDecryptionConfig() {
        return this.credentialsDecryptionConfig;
    }
    
    public void setCredentialsDecryptionConfig(final CredentialsDecryptionConfig credentialsDecryptionConfig) {
        this.credentialsDecryptionConfig = credentialsDecryptionConfig;
    }
    
    public ProxyConfig getProxyConfig() {
        return this.proxyConfig;
    }
    
    public void setProxyConfig(final ProxyConfig proxyConfig) {
        this.proxyConfig = proxyConfig;
    }
    
    public MetricsConfig getMetricsConfig() {
        return this.metricsConfig;
    }
    
    public void setMetricsConfig(final MetricsConfig metricsConfig) {
        this.metricsConfig = metricsConfig;
    }
    
    public ConcurrencyConfig getConcurrencyConfig() {
        return this.concurrencyConfig;
    }
    
    public void setConcurrencyConfig(final ConcurrencyConfig concurrencyConfig) {
        this.concurrencyConfig = concurrencyConfig;
    }
    
    public Map<String, String> getRegionEndPoints() {
        return this.regionEndPoints;
    }
    
    public void setRegionEndPoints(final Map<String, String> regionEndPoints) {
        this.regionEndPoints = regionEndPoints;
    }
    
    public String getMetricPrefix() {
        return this.metricPrefix;
    }
    
    public void setMetricPrefix(final String metricPrefix) {
        this.metricPrefix = metricPrefix;
    }
    
    public TaskSchedule getTaskSchedule() {
        return this.taskSchedule;
    }
    
    public void setTaskSchedule(final TaskSchedule taskSchedule) {
        this.taskSchedule = taskSchedule;
    }
    
    public String getCloudWatchMonitoring() {
        return this.cloudWatchMonitoring;
    }
    
    public void setCloudWatchMonitoring(final String cloudWatchMonitoring) {
        if (CloudWatchMonitoringLevel.BASIC.getLevel().equalsIgnoreCase(cloudWatchMonitoring) || CloudWatchMonitoringLevel.DETAILED.getLevel().equalsIgnoreCase(cloudWatchMonitoring)) {
            this.cloudWatchMonitoring = cloudWatchMonitoring;
        }
        else {
            this.cloudWatchMonitoring = CloudWatchMonitoringLevel.BASIC.getLevel();
        }
    }
    
    public int getCloudWatchMonitoringInterval() {
        return this.cloudWatchMonitoringInterval;
    }
    
    public void setCloudWatchMonitoringInterval(final int cloudWatchMonitoringInterval) {
        this.cloudWatchMonitoringInterval = cloudWatchMonitoringInterval;
    }

    public String getNamespace() {
        return this.namespace;
    }
    
    public void setNamespace(final String namespace) {
        this.namespace = namespace;
    }

    public List<Dimension> getDimensions() {
        return this.dimensions;
    }
    
    public void setDimensions(final List<Dimension> dimensions) {
        this.dimensions = dimensions;
    }
    
    public ControllerInfo getControllerInfo() {
        return this.controllerInfo;
    }
    
    public void setControllerInfo(final ControllerInfo controllerInfo) {
        this.controllerInfo = controllerInfo;
    }
    
    public Boolean getEnableHealthChecks() {
        return this.enableHealthChecks;
    }
    
    public void setEnableHealthChecks(final Boolean enableHealthChecks) {
        this.enableHealthChecks = enableHealthChecks;
    }
    
    public MetricCharSequenceReplacer getMetricCharSequenceReplacer() {
        return this.metricCharSequenceReplacer;
    }
    
    public void setMetricCharSequenceReplacer(final MetricCharSequenceReplacer metricCharSequenceReplacer) {
        this.metricCharSequenceReplacer = metricCharSequenceReplacer;
    }
    
    public String getEncryptionKey() {
        return this.encryptionKey;
    }
    
    public void setEncryptionKey(final String encryptionKey) {
        this.encryptionKey = encryptionKey;
    }
    
    public enum CloudWatchMonitoringLevel
    {
        BASIC("Basic"), 
        DETAILED("Detailed");
        
        private String level;
        
        private CloudWatchMonitoringLevel(final String level) {
            this.level = level;
        }
        
        public String getLevel() {
            return this.level;
        }
    }
}

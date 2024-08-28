
package com.bodyguards.security.sonarapi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "key",
    "rule",
    "severity",
    "component",
    "project",
    "line",
    "hash",
    "textRange",
    "flows",
    "status",
    "message",
    "effort",
    "debt",
    "tags",
    "creationDate",
    "updateDate",
    "type",
    "organization"
})
public class Issue {

    @JsonProperty("key")
    private String key;
    @JsonProperty("rule")
    private String rule;
    @JsonProperty("severity")
    private String severity;
    @JsonProperty("component")
    private String component;
    @JsonProperty("project")
    private String project;
    @JsonProperty("line")
    private Integer line;
    @JsonProperty("hash")
    private String hash;
    @JsonProperty("textRange")
    private TextRange textRange;
    @JsonProperty("flows")
    private List<Object> flows = null;
    @JsonProperty("status")
    private String status;
    @JsonProperty("message")
    private String message;
    @JsonProperty("effort")
    private String effort;
    @JsonProperty("debt")
    private String debt;
    @JsonProperty("tags")
    private List<Object> tags = null;
    @JsonProperty("creationDate")
    private String creationDate;
    @JsonProperty("updateDate")
    private String updateDate;
    @JsonProperty("type")
    private String type;
    @JsonProperty("organization")
    private String organization;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("key")
    public String getKey() {
        return key;
    }

    @JsonProperty("key")
    public void setKey(String key) {
        this.key = key;
    }

    @JsonProperty("rule")
    public String getRule() {
        return rule;
    }

    @JsonProperty("rule")
    public void setRule(String rule) {
        this.rule = rule;
    }

    @JsonProperty("severity")
    public String getSeverity() {
        return severity;
    }

    @JsonProperty("severity")
    public void setSeverity(String severity) {
        this.severity = severity;
    }

    @JsonProperty("component")
    public String getComponent() {
        return component;
    }

    @JsonProperty("component")
    public void setComponent(String component) {
        this.component = component;
    }

    @JsonProperty("project")
    public String getProject() {
        return project;
    }

    @JsonProperty("project")
    public void setProject(String project) {
        this.project = project;
    }

    @JsonProperty("line")
    public Integer getLine() {
        return line;
    }

    @JsonProperty("line")
    public void setLine(Integer line) {
        this.line = line;
    }

    @JsonProperty("hash")
    public String getHash() {
        return hash;
    }

    @JsonProperty("hash")
    public void setHash(String hash) {
        this.hash = hash;
    }

    @JsonProperty("textRange")
    public TextRange getTextRange() {
        return textRange;
    }

    @JsonProperty("textRange")
    public void setTextRange(TextRange textRange) {
        this.textRange = textRange;
    }

    @JsonProperty("flows")
    public List<Object> getFlows() {
        return flows;
    }

    @JsonProperty("flows")
    public void setFlows(List<Object> flows) {
        this.flows = flows;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("effort")
    public String getEffort() {
        return effort;
    }

    @JsonProperty("effort")
    public void setEffort(String effort) {
        this.effort = effort;
    }

    @JsonProperty("debt")
    public String getDebt() {
        return debt;
    }

    @JsonProperty("debt")
    public void setDebt(String debt) {
        this.debt = debt;
    }

    @JsonProperty("tags")
    public List<Object> getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(List<Object> tags) {
        this.tags = tags;
    }

    @JsonProperty("creationDate")
    public String getCreationDate() {
        return creationDate;
    }

    @JsonProperty("creationDate")
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    @JsonProperty("updateDate")
    public String getUpdateDate() {
        return updateDate;
    }

    @JsonProperty("updateDate")
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("organization")
    public String getOrganization() {
        return organization;
    }

    @JsonProperty("organization")
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "Issue{" +
                "key='" + key + '\'' +
                ", rule='" + rule + '\'' +
                ", severity='" + severity + '\'' +
                ", component='" + component + '\'' +
                ", project='" + project + '\'' +
                ", line=" + line +
                ", hash='" + hash + '\'' +
                ", textRange=" + textRange +
                ", flows=" + flows +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", effort='" + effort + '\'' +
                ", debt='" + debt + '\'' +
                ", tags=" + tags +
                ", creationDate='" + creationDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", type='" + type + '\'' +
                ", organization='" + organization + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}

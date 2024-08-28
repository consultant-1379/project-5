
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
    "total",
    "p",
    "ps",
    "rules"
})
public class RulesResponse {

    @JsonProperty("total")
    private Integer total;
    @JsonProperty("p")
    private Integer p;
    @JsonProperty("ps")
    private Integer ps;
    @JsonProperty("rules")
    private List<Rule> rules = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("total")
    public Integer getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(Integer total) {
        this.total = total;
    }

    @JsonProperty("p")
    public Integer getP() {
        return p;
    }

    @JsonProperty("p")
    public void setP(Integer p) {
        this.p = p;
    }

    @JsonProperty("ps")
    public Integer getPs() {
        return ps;
    }

    @JsonProperty("ps")
    public void setPs(Integer ps) {
        this.ps = ps;
    }

    @JsonProperty("rules")
    public List<Rule> getRules() {
        return rules;
    }

    @JsonProperty("rules")
    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

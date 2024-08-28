
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
    "sysTags"
})
public class Rule {

    @JsonProperty("key")
    private String key;
    @JsonProperty("sysTags")
    private List<String> sysTags = null;
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

    @JsonProperty("sysTags")
    public List<String> getSysTags() {
        return sysTags;
    }

    @JsonProperty("sysTags")
    public void setSysTags(List<String> sysTags) {
        this.sysTags = sysTags;
    }

}

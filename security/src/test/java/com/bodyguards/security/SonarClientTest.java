package com.bodyguards.security;

import com.bodyguards.security.sonarapi.*;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(MockitoExtension.class)
class SonarClientTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private SonarConfiguration sonarConfiguration;

    @InjectMocks
    private SonarClient sonarClient = new SonarClient();


    private static String url = "https://sonarqube.lmera.ericsson.se/api/rules/search?tags=owasp-a1,owasp-a2,owasp-a3,owasp-a4,owasp-a5,owasp-a6,owasp-a7,owasp-a8,owasp-a9,owasp-a10&languages=java";

    private static Logger logger = Logger.getLogger(SonarClient.class);

    private static IssueResponse issueResponse;

    private static RulesResponse rulesResponse;

    @BeforeAll
    public static void setup() {
        issueResponse = new IssueResponse();
        issueResponse.setTotal(3);
        issueResponse.setPaging(
                new Paging() {
                    {
                        setPageIndex(1);
                        setPageSize(100);
                        setTotal(3);
                    }
                });
        issueResponse.setIssues(new ArrayList<Issue>() {
            {
                add(new Issue() {{
                    setRule("java:S2092");
                    setSeverity("MINOR");
                    setProject("com-test-1");
                    setType("VULNERABILITY");
                    setComponent("com.test:src/main/java/com/test/test1.java");
                    setLine(1);
                    setHash("ffffffffffffffffffffffffffffffff");
                    setTextRange(new TextRange() {
                        {
                            setStartLine(1);
                            setEndLine(1);
                            setStartOffset(1);
                            setEndOffset(2);
                        }
                    });
                    setCreationDate("2020-07-16T10:52:49+0100");
                    setUpdateDate("2020-07-16T10:52:49+0100");
                    setDebt("Debt");
                    setMessage("Issue Message");
                    setStatus("OPEN");


                }});
                add(new Issue() {{
                    setRule("java:S4426");
                    setSeverity("BLOCKER");
                    setProject("com-test-1");
                    setType("VULNERABILITY");
                    setComponent("com.test:src/main/java/com/test/test2.java");
                    setLine(1);
                    setHash("ffffffffffffffffffffffffffffffff");
                    setTextRange(new TextRange() {
                        {
                            setStartLine(1);
                            setEndLine(1);
                            setStartOffset(1);
                            setEndOffset(2);
                        }
                    });
                    setCreationDate("2020-07-16T10:52:49+0100");
                    setUpdateDate("2020-07-16T10:52:49+0100");
                    setDebt("Debt");
                    setMessage("Issue Message");
                    setStatus("OPEN");
                }});
                add(new Issue() {{
                    setRule("java:S4426");
                    setSeverity("BLOCKER");
                    setProject("com-test-2");
                    setType("VULNERABILITY");
                    setComponent("com.test:src/main/java/com/test/test2.java");
                    setLine(1);
                    setHash("ffffffffffffffffffffffffffffffff");
                    setTextRange(new TextRange() {
                        {
                            setStartLine(1);
                            setEndLine(1);
                            setStartOffset(1);
                            setEndOffset(2);
                        }
                    });
                    setCreationDate("2020-07-16T10:52:49+0100");
                    setUpdateDate("2020-07-16T10:52:49+0100");
                    setDebt("Debt");
                    setMessage("Issue Message");
                    setStatus("OPEN");
                }});
            }
        });

        rulesResponse = new RulesResponse();
        rulesResponse.setTotal(2);
        rulesResponse.setP(1);
        rulesResponse.setPs(1);
        List<Rule> rules = new ArrayList<>() {
            {
                add(new Rule() {{
                    setKey("java:S2092");
                    setSysTags(new ArrayList<>() {{
                        add("owasp-a3");
                    }});
                }});
                add(new Rule() {{
                    setKey("java:S4426");
                    setSysTags(new ArrayList<>() {{
                        add("owasp-a1");
                    }});
                }});
            }
        };
        rulesResponse.setRules(rules);
    }

    @Test
    void that_getOWASProperty_Produce_A_Valid_Report() {
        Mockito.when(sonarConfiguration.getToken())
                .thenReturn("TOKEN");
        Mockito.when(restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<String>(Util.createHeaders("TOKEN", "")),
                RulesResponse.class
        )).thenReturn(new ResponseEntity<>(rulesResponse, null, HttpStatus.OK));
        Mockito
                .when(restTemplate.exchange(
                        "https://sonarqube.lmera.ericsson.se/api/issues/search?rules=java:S4426,java:S2092&types=VULNERABILITY",
                        HttpMethod.GET,
                        new HttpEntity<String>(Util.createHeaders("TOKEN", "")),
                        IssueResponse.class))
                .thenReturn(new ResponseEntity<>(issueResponse, null, HttpStatus.OK));

        Map<String, ReportService> reportsMap = sonarClient.getOWASProperty();
        int totalProject = reportsMap.keySet().size();
        reportsMap.get("com-test-1").calculateIndividualVulnerabilities();
        List<Integer> totals1 = reportsMap.get("com-test-1").calculateVulnerabilityTotals();
        assertThat(totalProject, is(equalTo(2)));
        assertThat(totals1.get(0), is(equalTo(1)));
        assertThat(totals1.get(1), is(equalTo(0)));
        assertThat(totals1.get(2), is(equalTo(0)));
        assertThat(totals1.get(3), is(equalTo(1)));
        logger.info(Util.getProjectsDataString(reportsMap));


    }
}
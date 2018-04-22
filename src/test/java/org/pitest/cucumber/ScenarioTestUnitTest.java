package org.pitest.cucumber;

import cucumber.api.junit.Cucumber;
import cucumber.runtime.Runtime;
import cucumber.runtime.model.CucumberScenario;
import gherkin.formatter.Formatter;
import gherkin.formatter.Reporter;
import gherkin.formatter.model.TagStatement;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.pitest.testapi.ResultCollector;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ScenarioTestUnitTest {

    @Mock
    private CucumberScenario scenario;

    @Mock
    private ResultCollector resultCollector;

    @Before
    public void setUp() {
        TagStatement gherkin = mock(TagStatement.class);
        when(scenario.getGherkinModel()).thenReturn(gherkin);
    }

    @Test
    public void should_run_scenario_and_call_collector_when_ran() {
        // given
        ScenarioTestUnit testUnit = new ScenarioTestUnit(HideFromJUnit.Concombre.class, getClass().getClassLoader(), scenario);

        // when
        testUnit.execute(resultCollector);

        // then
        verify(scenario, times(1)).run(any(Formatter.class), any(Reporter.class), any(Runtime.class));
    }

    private static class HideFromJUnit {

        @RunWith(Cucumber.class)
        private static class Concombre {
        }

    }
}
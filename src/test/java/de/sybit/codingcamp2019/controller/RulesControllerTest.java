package de.sybit.codingcamp2019.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.junit.Assert.assertEquals;

public class RulesControllerTest {

   @InjectMocks
   private RulesController rulesController;

   @Before
   public void setUp() {
      initMocks(this);
   }

   @Test
   public void showRules_returnRulesPage() {
      String page = rulesController.showRules();

      assertEquals("rules", page);
   }
}

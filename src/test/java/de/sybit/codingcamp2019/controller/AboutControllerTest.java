package de.sybit.codingcamp2019.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.junit.Assert.assertEquals;

public class AboutControllerTest {

   @InjectMocks
   private AboutController aboutController;

   @Before
   public void setUp() {
      initMocks(this);
   }

   @Test
   public void showAbout_returnAboutPage() {
      String page = aboutController.showAbout();

      assertEquals("about", page);
   }
}

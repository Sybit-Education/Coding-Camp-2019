package de.sybit.codingcamp2019.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ColorServiceImplTest {

   @Autowired
   private ColorService colorService;

   @Test
   public void getAmountOfColor_checkForSize() {
      Assert.assertEquals(8, colorService.getAmountOfRandomColor(8).size());
   }

   @Test
   public void getAmountOfColor_ZeroParam() {
      Assert.assertTrue(colorService.getAmountOfRandomColor(0).isEmpty());
   }

}

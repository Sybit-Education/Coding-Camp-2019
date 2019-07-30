package de.sybit.codingcamp2019.service;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ColorServiceImplTest {

   @Autowired
   private ColorService colorService;

   @Test
   public void getAmountOfColor_checkForSize() {
      Assert.assertEquals(4, colorService.getAmountOfRandomColor(4).size());
   }

   @Test
   public void getAmountOfColor_ZeroParam() {
      Assert.assertTrue(colorService.getAmountOfRandomColor(0).isEmpty());
   }

   @Test
   public void checkforDoubles_True() {
      List<String> colorList = new ArrayList<>();
      colorList.add("#b51783");
      colorList.add("#00ffff");
      colorList.add("#b51783"); //lila
      Assert.assertTrue(colorService.checkforDoubles(colorList, "#b51783"));
   }

   @Test
   public void checkforDoubles_False() {
      List<String> colorList = new ArrayList<>();
      colorList.add("#b51783");
      colorList.add("#00ffff");
      Assert.assertFalse(colorService.checkforDoubles(colorList, "#b51783"));
   }
}

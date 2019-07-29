package de.sybit.codingcamp2019.service;

import de.sybit.codingcamp2019.objects.ColorSelectionObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ColorServiceImpl implements ColorService {

   private static final Logger LOGGER = LoggerFactory.getLogger(ColorServiceImpl.class);

   @Override
   public List<String> getAmountOfRandomColor(int amount) {
      LOGGER.debug("--> getAmountOfRandomColor amount={}", amount);
      List<String> resultColorList = new ArrayList<>();
      for (int i = 0; i <= amount; i++) {
         String randomHexColor = getRandomHexColor();
        if (resultColorList.contains(randomHexColor)) {

           resultColorList.add(randomHexColor);
        }
      }
      //TODO

      LOGGER.debug("<-- getAmountOfRandomColor amount={}", amount);
      return resultColorList;
   }

   /**
    * Get random Color
    *
    * @return
    */
   private String getRandomHexColor() {
      LOGGER.debug("--> getRandomHexColor");
      List<String> allPossibleColors = getAllPossibleColors();
      Random random = new Random();
      int zufallszahl = random.nextInt(allPossibleColors.size());
      //TODO
      String randomColor = getAllPossibleColors().get(zufallszahl);

      LOGGER.debug("<-- getRandomHexColor: color = {}", randomColor);
      return randomColor;
   }

   @Override
   public List<ColorSelectionObject> getAllPossibleColorsForPicker() {
      LOGGER.debug("--> getAllPossibleColorsForPicker");

      List<ColorSelectionObject> colorSelectionObjects = new ArrayList<>();
      List<String> Farbenliste = getAllPossibleColors();
      //TODO

      LOGGER.debug("<-- getAllPossibleColorsForPicker");
      return colorSelectionObjects;
   }

   /**
    * Gets a List of every defined colors
    *
    * @return
    */
   private List<String> getAllPossibleColors() {
      LOGGER.debug("--> getAllPossibleColors");
      List<String> colorList = new ArrayList<>();
      colorList.add("#ff0000"); //red
      colorList.add("#ffff00"); //yellow
      colorList.add("#1212e3"); //blue
      colorList.add("#00ff00"); //green
      colorList.add("#fda50f"); //orange
      colorList.add("#b51783"); //lila
      colorList.add("#000000"); //black
      colorList.add("#00ffff"); //cyan
      LOGGER.debug("<-- getAllPossibleColors");
      return colorList;
   }
}

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
      
      //TODO
      String randomColor = null;
      
      LOGGER.debug("<-- getRandomHexColor: color = {}", randomColor);
      return randomColor;
   }

   @Override
   public List<ColorSelectionObject> getAllPossibleColorsForPicker() {
      LOGGER.debug("--> getAllPossibleColorsForPicker");
      
      List<ColorSelectionObject> colorSelectionObjects = new ArrayList<>();

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
      colorList.add("#0000ff"); //blue
      colorList.add("#00ff00"); //green
      colorList.add("#fda50f"); //orange
      colorList.add("#ff00ff"); //lila
      colorList.add("#000000"); //black
      colorList.add("#00ffff"); //cyan
      LOGGER.debug("<-- getAllPossibleColors");
      return colorList;
   }
}

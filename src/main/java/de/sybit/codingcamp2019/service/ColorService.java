package de.sybit.codingcamp2019.service;

import de.sybit.codingcamp2019.objects.ColorSelectionObject;

import java.util.List;

public interface ColorService {

   /**
    * Get random ColorList
    *
    * @param amount
    * @return
    */
   List<String> getAmountOfRandomColor(int amount);

   boolean checkForDoubles(List<String> resultColorList, String randomHexColor);

   String getRandomHexColor();
   /**
    * Get all possible colors for color picker
    *
    * @return List of ColorSelectionObjects
    */
   List<ColorSelectionObject> getAllPossibleColorsForPicker();
}

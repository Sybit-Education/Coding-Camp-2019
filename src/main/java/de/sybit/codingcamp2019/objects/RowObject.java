package de.sybit.codingcamp2019.objects;

import java.util.HashMap;
import java.util.Map;

public class RowObject {

   private Map<Integer, String> colorPos = new HashMap<>();
   private Map<Integer, int[]> feedback = new HashMap<>();

   public void addColor(Integer index, String color) {
      colorPos.put(index, color);
   }

   public String getColor(Integer index) {
      return colorPos.get(index);
   }

   public Map<Integer, String> getColorPos() {
      return colorPos;
   }

   public void addFeedback(int pos, int col) {
      int[] feedbackArray = new int[2];
      feedbackArray[0] = pos;
      feedbackArray[1] = col;
      feedback.put(0, feedbackArray);
   }

   public Map<Integer, int[]> getFeedback() {
      return feedback;
   }

}

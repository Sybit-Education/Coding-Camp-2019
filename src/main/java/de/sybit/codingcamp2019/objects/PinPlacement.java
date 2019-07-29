package de.sybit.codingcamp2019.objects;

import java.util.HashMap;
import java.util.Map;

public class PinPlacement {

   private static final String defaultColor = "#ff0000";

   private Game game;

   private Map<Integer, String> colors = new HashMap<>();

   public PinPlacement() {
      colors.put(0, defaultColor);
      colors.put(1, defaultColor);
      colors.put(2, defaultColor);
      colors.put(3, defaultColor);
   }

   public Game getGame(){ return game; }

   public void setGame(Game game) {
      this.game = game;
   }

   public Map<Integer, String> getColors() {
      return colors;
   }

   public void setColors(Map<Integer, String> colors) {
      this.colors = colors;
   }


}



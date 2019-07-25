package de.sybit.codingcamp2019.objects;


public class ColorSelectionObject {

   private String colorHex;
   private int colorId;

   public ColorSelectionObject(String colorHex, int colorId) {
      this.colorHex = colorHex;
      this.colorId = colorId;
   }

   public String getColorHex() {
      return colorHex;
   }

   public void setColorHex(String colorHex) {
      this.colorHex = colorHex;
   }

   public int getColorId() {
      return colorId;
   }

   public void setColorId(int colorId) {
      this.colorId = colorId;
   }


}

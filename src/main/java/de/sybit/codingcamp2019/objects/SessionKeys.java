package de.sybit.codingcamp2019.objects;

public enum SessionKeys {
   SESSION_GAME("game");

   private String key;

   SessionKeys(final String key) {
      this.key = key;
   }

   @Override
   public String toString() {
      return key;
   }
}

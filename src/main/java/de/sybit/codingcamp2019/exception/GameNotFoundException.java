package de.sybit.codingcamp2019.exception;

public class GameNotFoundException extends Exception {

   public GameNotFoundException() {
   }

   public GameNotFoundException(String message) {
      super(message);
   }
}

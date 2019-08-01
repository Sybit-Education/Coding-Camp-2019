package de.sybit.codingcamp2019.service;

import de.sybit.codingcamp2019.exception.GameNotFoundException;
import de.sybit.codingcamp2019.objects.Game;
import de.sybit.codingcamp2019.objects.PinPlacement;
import de.sybit.codingcamp2019.objects.ResponseObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.*;


@Service
public class FeedbackServiceImpl implements FeedbackService {


   private static final Logger LOGGER = LoggerFactory.getLogger(FeedbackServiceImpl.class);

   @Autowired
   private GameService gameService;

   @Override
   public ResponseObject getFeedbackFor(final HttpSession session, final PinPlacement requestPlacement) {
      LOGGER.debug("--> getFeedbackFor");
      final ResponseObject responseObject = new ResponseObject();

      try {
         final Game currentGame = gameService.getCurrentGameOf(session);
         final PinPlacement solution = currentGame.getPinSolution();
         final Map<Integer, String> solutionMap = solution.getColors();
         final Map<Integer, String> placementMap = requestPlacement.getColors();
         List<Object> solutionValues = new ArrayList<>(solutionMap.values());
         List<Object> placementValues = new ArrayList<>(placementMap.values());

         List<Object> correctPositions = calculateCorrectPositions(solutionValues, placementValues);

         for (Object position : correctPositions) {
            solutionValues.remove(position);
            placementValues.remove(position);

         }

         int correctColors = calculateCorrectColors(solutionValues, placementValues);
         responseObject.setCorrectColors(correctColors);
         responseObject.setCorrectPositions(correctPositions.size());

      } catch (GameNotFoundException e) {
         responseObject.setCorrectPositions(0);
         responseObject.setCorrectColors(0);
      }


      LOGGER.debug("<-- getFeedbackFor");
      return responseObject;


   }

   private List<Object> calculateCorrectPositions(final List<Object> solutionEntries, final List<Object> placementEntries) {

      List<Object> correctPositionValues = new ArrayList<>();
      for (int i = 0; i < solutionEntries.size(); i++) {
         if (placementEntries.get(i).equals(solutionEntries.get(i))) {
            correctPositionValues.add(placementEntries.get(i));
         }
      }
      return correctPositionValues;
   }

   private int calculateCorrectColors(final List<Object> solutionEntries, final List<Object> placementEntries) {

      int correctColors = 0;
      List<Object> correctColorsValues = new ArrayList<>();

      for (int i = 0; i < solutionEntries.size(); i++) {
         Object color = solutionEntries.get(i);
         if (!correctColorsValues.contains(color)) {
            for (int a = 0; a < solutionEntries.size(); a++) {
               if (color.equals(placementEntries.get(a))) {
                  correctColors++;
                  correctColorsValues.add(color);
                  break;
               }
            }
         }
      }

      return correctColors;
   }


}



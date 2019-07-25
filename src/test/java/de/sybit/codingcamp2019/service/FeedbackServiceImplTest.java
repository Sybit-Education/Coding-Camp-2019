package de.sybit.codingcamp2019.service;

import de.sybit.codingcamp2019.exception.GameNotFoundException;
import de.sybit.codingcamp2019.objects.Game;
import de.sybit.codingcamp2019.objects.PinPlacement;
import de.sybit.codingcamp2019.objects.ResponseObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

public class FeedbackServiceImplTest {

   @Mock
   private GameService gameService;

   @Mock
   private HttpSession session;

   @Mock
   private Game game;

   @Mock
   private PinPlacement solution;

   @InjectMocks
   private FeedbackServiceImpl feedbackService;

   @Before
   public void initMocks() {
      MockitoAnnotations.initMocks(this);
   }

   @Test
   public void getFeedback() throws GameNotFoundException{

      PinPlacement placement = new PinPlacement();
      Map<Integer, String> placementMap = new HashMap<>();
      placementMap.put(0, "color1");
      placementMap.put(1, "color4");
      placementMap.put(2, "color2");
      placementMap.put(3, "color1");
      placement.setColors(placementMap);

      when(gameService.getCurrentGameOf(session)).thenReturn(game);
      when(game.getPinSolution()).thenReturn(solution);
      Map<Integer, String> solutionMap = new HashMap<>();
      solutionMap.put(0, "color2");
      solutionMap.put(1, "color4");
      solutionMap.put(2, "color1");
      solutionMap.put(3, "color2");
      when(solution.getColors()).thenReturn(solutionMap);

      ResponseObject feedback = feedbackService.getFeedbackFor(session, placement);

      assertThat("CorrectColors", feedback.getCorrectColors() == 2);
      assertThat("CorrectPositions", feedback.getCorrectPositions() == 1);

   }

   @Test
   public void calculateCorrectPositionsTwoPositions() throws GameNotFoundException {
      PinPlacement placement = new PinPlacement();
      Map<Integer, String> placementMap = new HashMap<>();
      placementMap.put(0, "color1");
      placementMap.put(1, "color2");
      placementMap.put(2, "color2");
      placementMap.put(3, "color4");
      placement.setColors(placementMap);

      when(gameService.getCurrentGameOf(session)).thenReturn(game);
      when(game.getPinSolution()).thenReturn(solution);
      Map<Integer, String> solutionMap = new HashMap<>();
      solutionMap.put(0, "color1");
      solutionMap.put(1, "color2");
      solutionMap.put(2, "color5");
      solutionMap.put(3, "color6");
      when(solution.getColors()).thenReturn(solutionMap);

      ResponseObject feedback = feedbackService.getFeedbackFor(session, placement);

      assertThat("CorrectColors", feedback.getCorrectColors() == 0);
      assertThat("CorrectPositions", feedback.getCorrectPositions() == 2);
   }

   @Test
   public void calculateCorrectPositionsOnePositionTwoCorrectColor() throws GameNotFoundException {
      PinPlacement placement = new PinPlacement();
      Map<Integer, String> placementMap = new HashMap<>();
      placementMap.put(0, "color1");
      placementMap.put(1, "color6");
      placementMap.put(2, "color6");
      placementMap.put(3, "color4");
      placement.setColors(placementMap);

      when(gameService.getCurrentGameOf(session)).thenReturn(game);
      when(game.getPinSolution()).thenReturn(solution);
      Map<Integer, String> solutionMap = new HashMap<>();
      solutionMap.put(0, "color1");
      solutionMap.put(1, "color2");
      solutionMap.put(2, "color4");
      solutionMap.put(3, "color6");
      when(solution.getColors()).thenReturn(solutionMap);

      ResponseObject feedback = feedbackService.getFeedbackFor(session, placement);

      assertThat("CorrectColors", feedback.getCorrectColors() == 2);
      assertThat("CorrectPositions", feedback.getCorrectPositions() == 1);
   }

   @Test
   public void calculateCorrectPositionsOnePositionOneCorrectColor() throws GameNotFoundException {
      PinPlacement placement = new PinPlacement();
      Map<Integer, String> placementMap = new HashMap<>();
      placementMap.put(0, "color1");
      placementMap.put(1, "color2");
      placementMap.put(2, "color2");
      placementMap.put(3, "color4");
      placement.setColors(placementMap);

      when(gameService.getCurrentGameOf(session)).thenReturn(game);
      when(game.getPinSolution()).thenReturn(solution);
      Map<Integer, String> solutionMap = new HashMap<>();
      solutionMap.put(0, "color1");
      solutionMap.put(1, "color1");
      solutionMap.put(2, "color1");
      solutionMap.put(3, "color1");
      when(solution.getColors()).thenReturn(solutionMap);

      ResponseObject feedback = feedbackService.getFeedbackFor(session, placement);

      assertThat("CorrectColors", feedback.getCorrectColors() == 0);
      assertThat("CorrectPositions", feedback.getCorrectPositions() == 1);
   }
}

package de.sybit.codingcamp2019.service;

import de.sybit.codingcamp2019.objects.Game;
import de.sybit.codingcamp2019.objects.User;
import de.sybit.codingcamp2019.repository.ScoreRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class HighscoreServiceImplTest {

   @InjectMocks
   private HighscoreServiceImpl scoreService;

   @Mock
   private Game game;

   @Mock
   private User user;

   @Mock
   private ScoreRepository scoreRepository;

   @Before
   public void initMocks() {
      MockitoAnnotations.initMocks(this);
   }

   private List<Game> gamesList() {

      List<Game> gameList = new ArrayList<>();

      Game game1 = new Game();
      game1.setStartTime((LocalDateTime.of(2019, 4, 18, 10, 5, 20)));
      game1.setEndTime((LocalDateTime.of(2019, 4, 18, 10, 12, 44)));
      game1.setAttemptCount(7);

      Game game2 = new Game();
      game2.setStartTime((LocalDateTime.of(2019, 4, 18, 10, 15, 22)));
      game2.setEndTime((LocalDateTime.of(2019, 4, 18, 10, 17, 57)));
      game2.setAttemptCount(8);

      Game game3 = new Game();
      game3.setStartTime((LocalDateTime.of(2019, 4, 18, 10, 18, 10)));
      game3.setEndTime((LocalDateTime.of(2019, 4, 18, 10, 21, 34)));
      game3.setAttemptCount(5);

      gameList.add(game1);
      gameList.add(game2);
      gameList.add(game3);

      return gameList;
   }

   @Ignore
   @Test
   public void gameScoreTest() {

      LocalDateTime start = (LocalDateTime.of(2019, 4, 18, 10, 5, 44));
      LocalDateTime end = (LocalDateTime.of(2019, 4, 18, 10, 12, 44));
      int attemptCount = 7;

      when(game.getStartTime()).thenReturn(start);
      when(game.getEndTime()).thenReturn(end);
      when(game.getAttemptCount()).thenReturn(attemptCount);

      scoreService.gameScore(game);

      Assert.assertEquals(1460, scoreService.gameScore(game), 0);
      Assert.assertNotNull(game);

   }

   @Ignore
   @Test
   public void gameSessionHighScoreTest() {

      List<Game> gameList = gamesList();

      when(scoreRepository.findAllByUser(user)).thenReturn(gameList);

      int gameSessionHighScore = scoreService.gameSessionHighScore(user);

      Assert.assertEquals(6948, gameSessionHighScore, 0);

   }
}

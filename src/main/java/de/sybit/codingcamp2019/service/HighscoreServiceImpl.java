package de.sybit.codingcamp2019.service;

import de.sybit.codingcamp2019.objects.Game;
import de.sybit.codingcamp2019.objects.Highscore;
import de.sybit.codingcamp2019.objects.User;
import de.sybit.codingcamp2019.repository.HighscoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HighscoreServiceImpl implements HighscoreService {

   private static final Logger LOGGER = LoggerFactory.getLogger(HighscoreServiceImpl.class);

   @Autowired
   private HighscoreRepository highscoreRepository;


   @Override
   public double gameScore(Game game, int amountOfColor) {
      LOGGER.debug("--> gameScore");
      double gameScore = 0;

      //TODO


      LOGGER.debug("<-- gameScore");
      return gameScore;

   }

   @Override
   public double gameSessionHighScore(User user, int amountOfColor) {
      LOGGER.debug("--> gameSessionHighScore");


      double gameSessionHighScore = 0;
      //TODO
      List<Highscore> highscores = highscoreRepository.findAllById(new User());


      LOGGER.debug("<-- gameSessionHighScore");
      return gameSessionHighScore;
   }

   @Override
   public List<Highscore> getHighscores(int maxEntries){
      LOGGER.debug("--> getHighscores: maxEntries={}", maxEntries);
      List<Highscore> result = highscoreRepository.findAll(Sort.by("score").descending());

      LOGGER.debug("--> getHighscores: {}", result.size());
      return result;
   }
}

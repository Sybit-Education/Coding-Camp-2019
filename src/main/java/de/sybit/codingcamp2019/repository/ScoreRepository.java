package de.sybit.codingcamp2019.repository;

import de.sybit.codingcamp2019.objects.Game;
import de.sybit.codingcamp2019.objects.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Game, Long> {

   List<Game> findAllByUser(User user);

}

package de.sybit.codingcamp2019.repository;

import de.sybit.codingcamp2019.objects.Highscore;
import de.sybit.codingcamp2019.objects.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HighscoreRepository extends JpaRepository<Highscore, Long> {


   List<Highscore> findAllById(User user);



}

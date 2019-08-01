package de.sybit.codingcamp2019.repository;

import de.sybit.codingcamp2019.objects.Highscore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HighscoreRepository extends JpaRepository<Highscore, Long> {
}

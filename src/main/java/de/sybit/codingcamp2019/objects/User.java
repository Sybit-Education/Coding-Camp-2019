package de.sybit.codingcamp2019.objects;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "User")
public class User implements Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
   @GenericGenerator(name = "native", strategy = "native")
   @Column(name = "id", updatable = false, nullable = false)
   private Long id;

   @OneToMany(mappedBy = "user")
   private List<Game> games;

   public List<Game> getGames() {
      return games;
   }

   public void setGames(List<Game> games) {
      this.games = games;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }
}

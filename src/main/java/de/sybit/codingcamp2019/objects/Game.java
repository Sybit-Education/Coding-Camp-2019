package de.sybit.codingcamp2019.objects;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "Game")
public class Game implements Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
   @GenericGenerator(name = "native", strategy = "native")
   @Column(name = "id", updatable = false, nullable = false)
   private Long id;
   private int attemptCount;

   private GameStateEnum status;

   @Transient
   private PinPlacement pinSolution;
   private LocalDateTime startTime;
   private LocalDateTime endTime;
   private int score;

   @ManyToOne
   @JoinColumn
   private User user;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public int getAttemptCount() {
      return attemptCount;
   }

   public void setAttemptCount(int attemptCount) {
      this.attemptCount = attemptCount;
   }

   public PinPlacement getPinSolution() {
      return pinSolution;
   }

   public void setPinSolution(PinPlacement pinSolution) {
      this.pinSolution = pinSolution;
   }

   public User getUser() {
      return user;
   }

   public void setUser(User user) {
      this.user = user;
   }

   public LocalDateTime getStartTime() {
      return startTime;
   }

   public void setStartTime(final LocalDateTime startTime) {
      this.startTime = startTime;
   }

   public LocalDateTime getEndTime() {
      return endTime;
   }

   public void setEndTime(final LocalDateTime endTime) {
      this.endTime = endTime;
   }

   public GameStateEnum getStatus() {
      return status;
   }

   public void setStatus(GameStateEnum status) {
      this.status = status;
   }




}

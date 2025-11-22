package com.tuan.movieservice.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "movie_actors")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieActor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(targetEntity = Movie.class)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne(targetEntity = Genre.class)
    @JoinColumn(name = "actor_id")
    private Actor actor;
}
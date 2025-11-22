package com.tuan.movieservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tuan.movieservice.model.moviestatus.MovieStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "movies")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @NotNull
    private String name;
    private String poster;
    private String backdrop;
    @NotNull
    private String description;
    private String trailer;
    @NotNull
    private Date releaseDate;
    private Double voteAverage;
    private Integer voteCount;
    private MovieStatus status;
    @JsonIgnore
    @OneToMany(mappedBy = "movie", targetEntity = MovieGenre.class, cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<MovieGenre> movieGenres;

    @OneToMany(mappedBy = "movie", targetEntity = MovieActor.class, cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<MovieGenre> moveActors;

}
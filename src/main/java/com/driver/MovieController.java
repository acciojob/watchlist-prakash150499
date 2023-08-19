package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    /*
    1.Add a movie: POST /movies/add-movie
    Pass the Movie object as request body
    Return success message wrapped in a ResponseEntity object
    Controller Name - addMovie
    */

    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie) {
        movieService.addMovie(movie);
        return ResponseEntity.ok("Movie added Successfully.");
    }
    /*
   2.Add a director: POST /movies/add-director
    Pass the Director object as request body
    Return success message wrapped in a ResponseEntity object
    Controller Name - addDirector
   */
    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director) {
        movieService.addDirector(director);
        return ResponseEntity.ok("Director added successfully.");
    }
    /*
    3.Pair an existing movie and director: PUT /movies/add-movie-director-pair
    Pass movie name and director name as request parameters
    Return success message wrapped in a ResponseEntity object
    Controller Name - addMovieDirectorPair
    */
    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(
            @RequestParam String movieName,
            @RequestParam String directorName) {
        movieService.addMovieDirectorPair(movieName, directorName);
        return ResponseEntity.ok("Movie-Director pair added successfully.");
    }

    /*
    4.Get Movie by movie name: GET /movies/get-movie-by-name/{name}
    Pass movie name as path parameter
    Return Movie object wrapped in a ResponseEntity object
    Controller Name - getMovieByName
    */
    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable String name) {
        Movie movie = movieService.getMovieByName(name);
        return ResponseEntity.ok(movie);
    }
    /*
    5.Get Director by director name: GET /movies/get-director-by-name/{name}
    Pass director name as path parameter
    Return Director object wrapped in a ResponseEntity object
    Controller Name - getDirectorByName
     */
    @GetMapping("/get-director-by-name/{name}")

    public ResponseEntity<Director> getDirectorByName(@PathVariable String name) {
        Director director = movieService.getDirectorByName(name);
        return ResponseEntity.ok(director);
    }
    /*
    6.Get List of movies name for a given director name: GET /movies/get-movies-by-director-name/{director}
    Pass director name as path parameter
    Return List of movies name(List()) wrapped in a ResponseEntity object
    Controller Name - getMoviesByDirectorName
     */
    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable String director) {
        List<String> movies = movieService.getMoviesByDirectorName(director);
        return ResponseEntity.ok(movies);
    }
    /*
   7.Get List of all movies added: GET /movies/get-all-movies
   No params or body required
   Return List of movies name(List()) wrapped in a ResponseEntity object
   Controller Name - findAllMovies

    */
    @GetMapping("/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies() {
        List<String> movies = movieService.getAllMovieNames();
        return ResponseEntity.ok(movies);
    }
    /*
    8.Delete a director and its movies from the records: DELETE /movies/delete-director-by-name
    Pass director’s name as request parameter
    Return success message wrapped in a ResponseEntity object
    Controller Name - deleteDirectorByName

     */
    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam String directorName) {
        movieService.deleteDirectorByName(directorName);
        return ResponseEntity.ok("Director and associated movies deleted successfully.");
    }
    /*
   9.Delete all directors and all movies by them from the records: DELETE /movies/delete-all-directors
   No params or body required
   Return success message wrapped in a ResponseEntity object
   Controller Name - deleteAllDirectors
           (Note that there can be some movies on your watchlist that aren’t mapped to any of the director. Make sure you do not remove them.)
    */
    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors() {
        movieService.deleteAllDirectors();
        return ResponseEntity.ok("All directors and their associated movies deleted successfully.");
    }
}

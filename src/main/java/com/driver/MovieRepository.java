package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class MovieRepository {
    private HashMap<String,Movie>movie_db=new HashMap<>();
    private HashMap<String,Director>director_db=new HashMap<>();
    private HashMap<String, List<String>> directorMoviesMap = new HashMap<>();

     //API-1
    public void addMovie(Movie movie) {
        movie_db.put(movie.getName(),movie);

    }
    //API-2
    public void addDirector(Director director) {
        director_db.put(director.getName(),director);
    }


    //API-3
    public void addMovieDirectorPair(String movieName, String directorName) {
        if (directorMoviesMap.containsKey(directorName)) {
            directorMoviesMap.get(directorName).add(movieName);
        } else {
            List<String> movies = new ArrayList<>();
            movies.add(movieName);
            directorMoviesMap.put(directorName, movies);
        }
    }
    //API-4
    public Movie getMovieByName(String name) {return movie_db.get(name);}
    //API-5
    public Director getDirectorByName(String name) {return director_db.get(name);}

    //API-6
    public List<String> getMoviesByDirectorName(String director) {
        return directorMoviesMap.get(director);
    }
    //API-7
    public List<String> getAllMovieNames() {
        List<String> keysList = new ArrayList<>(movie_db.keySet());
        return keysList;
    }
    //API-8
    public void deleteDirectorByName(String directorName) {
        List<String>movies=directorMoviesMap.get(directorName);
        directorMoviesMap.remove(directorName);
        director_db.remove(directorName);
        for(String mve:movies)
        {
            movie_db.remove(mve);
        }
    }

    public void deleteAllDirectors() {
        for(String key:directorMoviesMap.keySet())
        {
            List<String> movie_list=directorMoviesMap.get(key);
            for(String movie:movie_list)
            {
                movie_db.remove(movie);
            }
        }
        directorMoviesMap.clear();
        director_db.clear();
    }
    //API-9
    
}


package org.imdb.pojo.movie_search;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class MovieSearchByNameResponseSuccess{
    @JsonProperty("Search")
    public List<MovieNames> search;
    public String totalResults;
    @JsonProperty("Response")
    public String response;
}


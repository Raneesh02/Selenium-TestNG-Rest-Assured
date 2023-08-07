package org.imdb.pojo.movie_search;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieNames {
    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getType() {
        return type;
    }

    public String getPoster() {
        return poster;
    }

    @JsonProperty("Title")
    public String title;
    @JsonProperty("Year")
    public String year;
    public String imdbID;
    @JsonProperty("Type")
    public String type;
    @JsonProperty("Poster")
    public String poster;

}


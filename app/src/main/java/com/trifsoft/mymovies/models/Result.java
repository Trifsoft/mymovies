
package com.trifsoft.mymovies.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "result", foreignKeys = {
})
public class Result {

    @SerializedName("adult")
    @Expose
    @Ignore
    private Boolean adult;
    @SerializedName("backdrop_path")
    @Expose
    @Ignore
    private String backdropPath;
    @SerializedName("genre_ids")
    @Expose
    @Ignore
    private List<Integer> genreIds;
    @SerializedName("id")
    @Expose
    @ColumnInfo(name = "result_id")
    @PrimaryKey
    private Integer id;
    @SerializedName("original_language")
    @Expose
    @Ignore
    private String originalLanguage;
    @SerializedName("original_title")
    @Expose
    @Ignore
    private String originalTitle;
    @SerializedName("overview")
    @Expose
    @Ignore
    private String overview;
    @SerializedName("popularity")
    @Expose
    @Ignore
    private Double popularity;
    @SerializedName("poster_path")
    @Expose
    @ColumnInfo(name = "posterPath")
    private String posterPath;
    @SerializedName("release_date")
    @Expose
    @Ignore
    private String releaseDate;
    @SerializedName("title")
    @Expose
    @ColumnInfo(name = "title")
    private String title;
    @SerializedName("video")
    @Expose
    @Ignore
    private Boolean video;
    @SerializedName("vote_average")
    @Expose
    @Ignore
    private Double voteAverage;
    @SerializedName("vote_count")
    @Expose
    @Ignore
    private Integer voteCount;

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

}

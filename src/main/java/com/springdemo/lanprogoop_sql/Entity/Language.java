package com.springdemo.lanprogoop_sql.Entity;
/**
 * Language entity represents a programming language with properties like name,
 * release year, creator, and paradigm.
 */
public class Language {
    private Long id;
    private String name;
    private Integer releaseYear;
    private String creator;
    private String paradigm;

    // Constructors, getters, and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getParadigm() {
        return paradigm;
    }

    public void setParadigm(String paradigm) {
        this.paradigm = paradigm;
    }

    public Language() {
    }

    public Language(Long id, String name, Integer releaseYear, String creator, String paradigm) {
        this.id = id;
        this.name = name;
        this.releaseYear = releaseYear;
        this.creator = creator;
        this.paradigm = paradigm;
    }
}
package com.springdemo.lanprogoop_sql.Service;

import com.springdemo.lanprogoop_sql.Entity.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing languages in the database.
 * This class provides CRUD operations for languages using direct SQL queries.
 */
@Service
public class LanguageService {
    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructs a new LanguageService with the given JdbcTemplate.
     *
     * @param jdbcTemplate the JdbcTemplate to be used for database operations
     */
    @Autowired
    public LanguageService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Retrieves all languages from the database.
     *
     * @return a list of all languages
     */
    public List<Language> findAll() {
        String sql = "SELECT * FROM languages";
        return jdbcTemplate.query(sql, languageRowMapper());
    }

    /**
     * Finds a language by its ID.
     *
     * @param id the ID of the language to find
     * @return an Optional containing the found language if it exists, or empty otherwise
     */
    public Optional<Language> findById(Long id) {
        String sql = "SELECT * FROM languages WHERE id = ?";
        Language language = jdbcTemplate.queryForObject(sql, new Object[]{id}, languageRowMapper());
        return Optional.ofNullable(language);
    }

    /**
     * Saves a language to the database. If the language has an ID, it updates the existing language; otherwise, it inserts a new one.
     *
     * @param language the language to save
     * @return the saved language
     */
    public Language save(Language language) {
        if (language.getId() == null) {
            String sql = "INSERT INTO languages (name, release_year, creator, paradigm) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(sql, language.getName(), language.getReleaseYear(), language.getCreator(), language.getParadigm());
        } else {
            String sql = "UPDATE languages SET name = ?, release_year = ?, creator = ?, paradigm = ? WHERE id = ?";
            jdbcTemplate.update(sql, language.getName(), language.getReleaseYear(), language.getCreator(), language.getParadigm(), language.getId());
        }
        return language;
    }

    /**
     * Deletes a language from the database by its ID.
     *
     * @param id the ID of the language to delete
     */
    public void delete(Long id) {
        String sql = "DELETE FROM languages WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    /**
     * Maps a row from the SQL result set to a Language object.
     * This is used by the JdbcTemplate to convert rows of the result set into Language objects.
     *
     * @return a row mapper for converting rows into Language objects
     */
    private RowMapper<Language> languageRowMapper() {
        return (rs, rowNum) -> {
            Language language = new Language();
            language.setId(rs.getLong("id"));
            language.setName(rs.getString("name"));
            language.setReleaseYear(rs.getInt("release_year"));
            language.setCreator(rs.getString("creator"));
            language.setParadigm(rs.getString("paradigm"));
            return language;
        };
    }
}
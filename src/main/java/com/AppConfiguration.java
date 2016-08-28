package com;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by anilkumar.r on 05/08/16.
 */
public class AppConfiguration extends Configuration {

    @Valid
    @NotNull
    @JsonProperty("excaliburMaster")
    private DataSourceFactory excaliburDatabase;


    @NotNull
    private String login;
    @NotNull
    private String password;

    @JsonProperty
    public String getLogin() {
        return login;
    }
    @JsonProperty
    public String getPassword() {
        return password;
    }


    public DataSourceFactory getExcaliburDatabase() {
        return excaliburDatabase;
    }

    public void setExcaliburDatabase(DataSourceFactory excaliburDatabase) {
        this.excaliburDatabase = excaliburDatabase;
    }
}

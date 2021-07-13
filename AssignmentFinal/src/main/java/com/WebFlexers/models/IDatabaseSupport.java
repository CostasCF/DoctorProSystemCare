package com.WebFlexers.models;

import com.WebFlexers.Query;

import java.sql.Connection;

public interface IDatabaseSupport {
    void addToDatabase(Connection connection);
    void removeFromDatabase(Connection connection);
}

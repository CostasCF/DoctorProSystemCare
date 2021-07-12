package com.WebFlexers.models;

import com.WebFlexers.Query;

public interface IDatabaseSupport {
    void addToDatabase(Query query);
    void removeFromDatabase(Query query);
}

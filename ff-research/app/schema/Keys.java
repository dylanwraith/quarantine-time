/*
 * This file is generated by jOOQ.
 */
package schema;


import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;

import schema.tables.Author;
import schema.tables.FlywaySchemaHistory;
import schema.tables.records.AuthorRecord;
import schema.tables.records.FlywaySchemaHistoryRecord;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code></code> schema.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<AuthorRecord> PK_AUTHOR = UniqueKeys0.PK_AUTHOR;
    public static final UniqueKey<FlywaySchemaHistoryRecord> PK_FLYWAY_SCHEMA_HISTORY = UniqueKeys0.PK_FLYWAY_SCHEMA_HISTORY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class UniqueKeys0 {
        public static final UniqueKey<AuthorRecord> PK_AUTHOR = Internal.createUniqueKey(Author.AUTHOR, "pk_author", new TableField[] { Author.AUTHOR.ID }, true);
        public static final UniqueKey<FlywaySchemaHistoryRecord> PK_FLYWAY_SCHEMA_HISTORY = Internal.createUniqueKey(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY, "pk_flyway_schema_history", new TableField[] { FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.INSTALLED_RANK }, true);
    }
}

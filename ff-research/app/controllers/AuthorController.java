package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.AuthorModel;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import utils.Util;

import javax.inject.Inject;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import static schema.Tables.*;
import java.sql.*;

public class AuthorController extends Controller {

    private HttpExecutionContext ec;
    private String databaseUrl = "jdbc:sqlite:resources\\db\\library.db";

    @Inject
    public AuthorController(HttpExecutionContext ec) {
        try {
            Class.forName("org.sqlite.JDBC");
            this.ec = ec;
        }
        catch(Exception e) {
            System.out.println("Error: unable to load driver class!");
            System.exit(1);
        }
    }

    public CompletionStage<Result> create(Http.Request request) {
        JsonNode json = request.body().asJson();
        return supplyAsync(() -> {
            if (json == null) {
                return badRequest(Util.createResponse("Expecting Json data", false));
            }
            try (Connection conn = DriverManager.getConnection(databaseUrl)) {
                AuthorModel author = Json.fromJson(json,  AuthorModel.class);
                DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
                int result = create
                        .insertInto(AUTHOR, AUTHOR.ID, AUTHOR.FIRST_NAME, AUTHOR.LAST_NAME, AUTHOR.ADDRESS)
                        .values(author.getId(), author.getFirstName(), author.getLastName(), "2839 Barnard Street")
                        .execute();
                return ok(Util.createResponse("Successfully added author to database.", true));
            }
            catch (Exception e) {
                e.printStackTrace();
                return internalServerError(Util.createResponse("Could not add author to database.", false));
            }
        }, ec.current());
    }

    public CompletionStage<Result> update(Http.Request request) {
        JsonNode json = request.body().asJson();
        return supplyAsync(() -> {
            if (json == null) {
                return badRequest(Util.createResponse("Expecting Json data", false));
            }
            try (Connection conn = DriverManager.getConnection(databaseUrl)) {
                AuthorModel author = Json.fromJson(json,  AuthorModel.class);
                DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
                int result = create
                        .update(AUTHOR)
                        .set(AUTHOR.FIRST_NAME, author.getFirstName())
                        .set(AUTHOR.LAST_NAME, author.getLastName())
                        .where(AUTHOR.ID.eq(author.getId()))
                        .execute();
                if (result > 0) {
                    return ok(Util.createResponse("Successfully updated author with id " + author.getId(), true));
                }
                return notFound(Util.createResponse("Author with id:" + author.getId() + " not found", false));
            }
            catch (Exception e) {
                e.printStackTrace();
                return internalServerError(Util.createResponse("Could not add author to database.", false));
            }
        }, ec.current());
    }

    public CompletionStage<Result> retrieve(int id) {
        return supplyAsync(() -> {
            try (Connection conn = DriverManager.getConnection(databaseUrl)) {
                DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
                String result = create
                        .select()
                        .from(AUTHOR)
                        .where(AUTHOR.ID.eq(id))
                        .fetch()
                        .formatJSON();
                return ok(Util.createResponse(result, true));
            }
            catch (Exception e) {
                e.printStackTrace();
                return internalServerError(Util.createResponse("Could not retrieve data.", false));
            }
        }, ec.current());
    }

    public CompletionStage<Result> delete(int id) {
        return supplyAsync(() -> {
            try (Connection conn = DriverManager.getConnection(databaseUrl)) {
                DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
                int value = create
                        .delete(AUTHOR)
                        .where(AUTHOR.ID.eq(id))
                        .execute();
                if (value > 0) {
                    return ok(Util.createResponse("Student with id:" + id + " deleted", true));
                }
                return notFound(Util.createResponse("Student with id:" + id + " not found", false));
            }
            catch (Exception e) {
                e.printStackTrace();
                return internalServerError(Util.createResponse("Could not delete data.", false));
            }
        }, ec.current());
    }

    public CompletionStage<Result> listStudents() {
        return supplyAsync(() -> {
            try (Connection conn = DriverManager.getConnection(databaseUrl)) {
                DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
                String result = create
                        .select()
                        .from(AUTHOR)
                        .fetch()
                        .formatJSON();
                return ok(Util.createResponse(result, true));
            }
            catch (Exception e) {
                e.printStackTrace();
                return internalServerError(Util.createResponse("Could not retrieve data.", false));
            }
        }, ec.current());
    }

}

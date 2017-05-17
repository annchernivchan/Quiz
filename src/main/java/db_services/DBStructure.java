package db_services;

import db_connection.Connector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBStructure {

    public void createStructure(){

        Connection connection = Connector.getConnection();
        Statement statement = null;
        try {
            String sql = "create table questions\n" +
                    "(\n" +
                    "\tid VARCHAR(64) not null\n" +
                    "\t\tconstraint questions_pkey\n" +
                    "\t\t\tprimary key,\n" +
                    "\ttext varchar(255) not null,\n" +
                    "\tpoint double precision not null,\n" +
                    "\tquestion_type_id integer not null,\n" +
                    "\tverified boolean default false not null\n" +
                    ")\n" +
                    ";\n" +
                    "\n" +
                    "create unique index questions_id_uindex\n" +
                    "\ton questions (id)\n" +
                    ";" +

                    "create table tasks\n" +
                    "(\n" +
                    "\tid VARCHAR(64) not null\n" +
                    "\t\tconstraint tasks_pkey\n" +
                    "\t\t\tprimary key,\n" +
                    "\tname varchar(60) not null,\n" +
                    "\ttotal_point double precision\n" +
                    ")\n" +
                    ";\n" +

                    "create table task_questions\n" +
                    "(\n" +
                    "\ttask_id varchar(64) not null,\n" +
                    "\tquestion_id varchar(64) not null\n" +
                    ")\n" +
                    ";" +

                    "create table question_type\n" +
                    "(\n" +
                    "\ttype varchar(50) not null,\n" +
                    "\tid serial not null\n" +
                    "\t\tconstraint question_type_id_pk\n" +
                    "\t\t\tprimary key\n" +
                    ")\n" +
                    ";\n" +
                    "\n" +
                    "create unique index question_type_id_uindex\n" +
                    "\ton question_type (id)\n" +
                    ";\n" +
                    "\n" +
                    "INSERT INTO question_type VALUES ('ONE_RIGHT_ANSWER');"+
                    "INSERT INTO question_type VALUES ('MULTI_CHOICE');"+

                    "create table answers\n" +
                    "(\n" +
                    "\tid VARCHAR(64) not null\n" +
                    "\t\tconstraint answers_pkey\n" +
                    "\t\t\tprimary key,\n" +
                    "\tquestion_id varchar(64) not null,\n" +
                    "\ttext varchar(100),\n" +
                    "\tweight double precision not null\n" +
                    ")\n" +
                    ";\n" +
                    "\n" +
                    "create unique index answers_id_uindex\n" +
                    "\ton answers (id)\n" +
                    ";\n";

            statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connector.close(connection, statement);
        }

    }

}

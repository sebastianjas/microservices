package com.poc.springbatch.batch.reader;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@StepScope
@Component
public class QueryInput implements PreparedStatementSetter {

    @Value("#{jobParameters[limitDate]}")
    String limitDate;

    @Override
    public void setValues(PreparedStatement preparedStatement) throws SQLException {
        var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        var localDate = LocalDate.parse(limitDate, formatter);
        var sqlDate = new java.sql.Date(localDate.getDayOfYear(),
                localDate.getMonthValue(),
                localDate.getYear());
        preparedStatement.setDate(1, sqlDate);
    }
}

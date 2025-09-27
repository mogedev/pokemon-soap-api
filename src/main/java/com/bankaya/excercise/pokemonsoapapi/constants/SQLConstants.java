package com.bankaya.excercise.pokemonsoapapi.constants;

public class SQLConstants {

    public final static String BINNACLE_TABLE_NAME = "POKE_API_BINNACLE";
    public final static String BINNACLE_ID_COLUMN = "ID";
    public final static String ORIGIN_IP_COLUMN = "ORIGIN_IP";
    public final static String REQUEST_DATE_COLUMN = "REQUEST_DATE";
    public final static String EXECUTED_METHOD_COLUMN = "EXECUTED_METHOD";
    public final static String DURATION_TIME_COLUMN = "DURATION_TIME";
    public final static String REQUEST_COLUMN = "REQUEST";
    public final static String RESPONSE_COLUMN = "RESPONSE";

    public final static String INSERT_BINNACLE_LOG = """
            INSERT INTO POKE_API_BINNACLE(ORIGIN_IP, REQUEST_DATE, EXECUTED_METHOD, DURATION_TIME, REQUEST, RESPONSE)
            VALUES (:originIp, :requestDate, :executedMethod, :durationTime, :request, :response)
           \s""";

    public final static String ORIGIN_IP_PARAM = "originIp";
    public final static String REQUEST_DATE_PARAM = "requestDate";
    public final static String EXECUTED_METHOD_PARAM = "executedMethod";
    public final static String DURATION_TIME_PARAM = "durationTime";
    public final static String REQUEST_PARAM = "request";
    public final static String RESPONSE_PARAM = "response";
}

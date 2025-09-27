package com.bankaya.excercise.pokemonsoapapi.constants;

public final class SQLConstants {

    private SQLConstants() {
        // Private constructor to prevent instantiation
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static final String BINNACLE_TABLE_NAME = "POKE_API_BINNACLE";
    public static final String BINNACLE_ID_COLUMN = "ID";
    public static final String ORIGIN_IP_COLUMN = "ORIGIN_IP";
    public static final String REQUEST_DATE_COLUMN = "REQUEST_DATE";
    public static final String EXECUTED_METHOD_COLUMN = "EXECUTED_METHOD";
    public static final String DURATION_TIME_COLUMN = "DURATION_TIME";
    public static final String REQUEST_COLUMN = "REQUEST";
    public static final String RESPONSE_COLUMN = "RESPONSE";

    public static final String INSERT_BINNACLE_LOG = """
            INSERT INTO POKE_API_BINNACLE(ORIGIN_IP, REQUEST_DATE, EXECUTED_METHOD, DURATION_TIME, REQUEST, RESPONSE)
            VALUES (:originIp, :requestDate, :executedMethod, :durationTime, :request, :response)
           \s""";

    public static final String ORIGIN_IP_PARAM = "originIp";
    public static final String REQUEST_DATE_PARAM = "requestDate";
    public static final String EXECUTED_METHOD_PARAM = "executedMethod";
    public static final String DURATION_TIME_PARAM = "durationTime";
    public static final String REQUEST_PARAM = "request";
    public static final String RESPONSE_PARAM = "response";
}

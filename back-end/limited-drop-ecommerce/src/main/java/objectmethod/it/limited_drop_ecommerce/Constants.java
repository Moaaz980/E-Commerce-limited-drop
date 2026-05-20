package objectmethod.it.limited_drop_ecommerce;

public final class Constants {
    private Constants() {} // Evito l'istanzazione
    // Costanti del token jwt
    public static final String JWT_SECRET = "qVlupUTcJQyA7T3vTH2Opk31JxCNj86NL3CxPfo6SXkkuOO1tgaZKRHtiCwalNsJb0u6qN8xZDAT8mHxNtmOlrMBF72EQCcDoMXYFSUr8aoTt12ipkQPyrZAfOZpQYfT";
    public static final Long EXPIRATION = 86400000L;
    public static final String ROLE = "role";
    // Questo dice a quello che legge che questo utente non ha una password locale e non deve authenticarsi con credenziali tradizionali
    public static final String OAUTHUSER2_USER = "oatuhuser2";
}

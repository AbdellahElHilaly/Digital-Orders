package com.youcode.digitalorders.shared.Const;

public class AppEndpoints {
    private static final  String VERSION_1 = "/api/v1";
    private static final  String VERSION_2 = "/api/v2";
    private static final  String VERSION_3 = "/api/v3";

    private static final  String VERSION = VERSION_1;
    public static final  String USER_ENDPOINT = VERSION + "/users"; // http://localhost:8080/api/v1/users

    public static final  String Contrat_ENDPOINT = VERSION + "/contrats";
    public static final  String DEMAND_ENDPOINT = VERSION + "/demands";
    public static final  String FIREBASE_ENDPOINT = VERSION + "/firebase";
    public static final  String DEVIS_ENDPOINT = VERSION + "/devis";

}

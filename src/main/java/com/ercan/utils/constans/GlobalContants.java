package com.ercan.utils.constans;

public class GlobalContants {

    private GlobalContants() {
        throw new IllegalAccessError("GlobalContants");
    }

    /* Default system user account*/
    public static final String SYSTEM_ACCOUNT = "system";

    /* Jwt Token Expiry Time */
    public static final Long EXPIRATION_MILLIS = 360000L;

    /* YES or NO */
    public static final int NO = 0;
    public static final int YES = 1;
    public static final String NO_STR = "No";
    public static final String YES_STR = "Yes";

    /* TRUE or FALSE */
    public static final boolean TRUE = true;
    public static final boolean FALSE = false;
    public static final String TRUE_STR = "true";
    public static final String FALSE_STR = "false";

}

package com.ercan.utils.constans;

public class DatabaseConstants {

    private DatabaseConstants(){throw new IllegalAccessError("DatabaseConstants");}

    public static class Roles {
        private Roles(){throw new IllegalAccessError("DatabaseConstants.Roles");}

        public static final String ROLE_ADMIN = "ADMIN";
        public static final String ROLE_USER = "USER";
    }

    public static class RecordStatus {
        private RecordStatus(){throw new IllegalAccessError("DatabaseConstants.RecordStatus");}

        public static final int ACTIVE = 1;
        public static final int PASSIVE = 0;
    }

    public static class EnableStatus {
        private EnableStatus(){throw new IllegalAccessError("DatabaseConstants.EnableStatus");}

        public static final int ACTIVE = 1;
        public static final int PASSIVE = 0;
    }

}

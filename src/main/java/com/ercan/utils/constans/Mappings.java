package com.ercan.utils.constans;

public class Mappings {
    public static final String BASE_PATH = "/api";
    public static final String SAVE = "/save";
    public static final String UPDATE = "/update";
    public static final String BY_ID = "/{id}";
    public static final String ALL = "/all";
    public static final String USER_PATH = BASE_PATH + "/user";
    public static final String BY_USER_ID = "/user/{userId}";
    public static final String BY_USERNAME = "/{username}";
    public static final String AUTH = BASE_PATH + "/auth";
    public static final String CURRENT_USER = "/current-user";
    public static final String SIGNIN = "/signin";
    public static final String SIGNUP = "/signup";
    public static final String CATEGORY_PATH = BASE_PATH + "/category";
    public static final String BY_CATEGORY_ID="/by-category/{categoryId}";
    public static final String QUIZ_PATH = BASE_PATH + "/quiz";
    public static final String BY_QUIZ_ID = "/by-quiz/{quizId}";
    public static final String QUESTION_PATH = BASE_PATH + "/question";
    public static final String BY_QUESTION_ID = "/by-question/{questionId}";
    public static final String IMAGE_UPLOAD_BY_USER_ID = "/image/upload/{userId}";
    public static final String IMAGE_INFO_BY_USER_ID = "/image/info/{userId}";
    public static final String IMAGE_VIEW_BY_ID = "/image/view/{userId}/{imageId}";

}

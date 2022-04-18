package com.ercan.utils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GlobalUtils {

    private GlobalUtils() {
        throw new IllegalAccessError("GlobalUtils");
    }

    /* null check */
    public static boolean isNull(Object obj) {
        return (null == obj) ? true : false;
    }

    /* not null check */
    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    /* null or empty check */
    public static boolean isNullOrEmpty(Collection c) {
        return (c == null || c.size() == 0) ? true : false;
    }

    /* null or zero lenght check */
    public static boolean isNullOrZeroLenght(String str) {
        return (null == str || "".equals(str.trim())) ? true : false;
    }

    /* null or zero size check collection*/
    public static boolean isNullOrZeroSize(Collection<? extends Object> c) {
        return isNull(c) || c.isEmpty();
    }

    /* email format control */
    public static boolean emailFormat(String email) {
        boolean flag = true;
        final String pattern1 = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\\\.[a-z]{2,4}$"; //"(\\w+)([\\-+.][\\w]+)*@(\\w[\\-\\w]*\\.){1,5}([A-Za-z]){2,6}";
        final Pattern pattern = Pattern.compile(pattern1);
        final Matcher mat = pattern.matcher(email);
        if (!mat.find()) {
            flag = false;
        }
        return flag;
    }

    /* password format control */
    public static boolean passwordFormat(String password) {
        boolean tag = true;
        final String pattern1 = "^[a-zA-Z0-9]{6,12}$";
        final Pattern pattern = Pattern.compile(pattern1);
        final Matcher mat = pattern.matcher(password);
        if (!mat.find()) {
            tag = false;
        }
        return tag;
    }

    /* phone format control */
    public static boolean phoneFormat(String phone) {
        boolean tag = true;
        final String pattern1 = "^((\\\\+91-?)|0)?[0-9]{10}$"; //"\\d{3,4}-\\d{8}$|\\d{11}";
        final Pattern pattern = Pattern.compile(pattern1);
        final Matcher mat = pattern.matcher(phone);
        if (!mat.find()) {
            tag = false;
        }
        return tag;
    }

    /* phone format control */
    static public boolean isPhoneNumber(String number) {
        // TODO Singleton is more efficent
        Pattern phoneNumPattern = Pattern.compile("\\+?[0-9]+"); // Begins (or not) with a plus and then the number
        Matcher matcher = phoneNumPattern.matcher(number);
        if (matcher.matches())
            return true;
        else
            return false;
    }

    /* list convert to string */
    public static <T> String listToString(List<T> list) {
        StringBuilder sb = new StringBuilder();
        sb.append(list.get(0).toString());
        list = list.subList(1, list.size());
        for (T t : list) {
            sb.append(", ").append(t.toString());
        }
        return sb.toString();
    }

    /* string value check in array */
    public static boolean isContainStr(String[] arrayStr, String str) {
        List list = Arrays.asList(arrayStr);
        if (list.contains(str)) {
            return true;
        } else {
            return false;
        }
    }

    /* custom create token */
    public static String createToken() {
        StringBuffer b = new StringBuffer();
        StringBuffer numBuff = new StringBuffer("1,2,3,4,5,6,7,8,9,0");
        String[] numArr = numBuff.toString().split(",");
        java.util.Random r;
        int k;
        for (int i = 0; i < 6; i++) {
            r = new java.util.Random();
            k = r.nextInt();
            b.append(String.valueOf(numArr[Math.abs(k % 9)]));
        }
        StringBuffer dbuf = new StringBuffer("a,b,c,d,e,f,g,h,i,g,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z");
        dbuf.append(",A,B,C,D,E,F,G,H,I,G,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z");
        String[] dArr = dbuf.toString().split(",");
        for (int i = 0; i < 2; i++) {
            r = new java.util.Random();
            k = r.nextInt();
            b.append(String.valueOf(dArr[Math.abs(k % 51)]));
        }
        return b.toString();
    }

}

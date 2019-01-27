package com.soon.test.myapplication.database;

import java.io.Serializable;

public class Item implements Serializable{
    public static final String TABLE_NAME = "item";
    public static final String COLUMN_NAME_STR1 = "str1";
    public static final String COLUMN_NAME_STR2 = "str2";
    public static final String COLUMN_NAME_STR3 = "str3";
    public static final String COLUMN_NAME_STR4 = "str4";
    public static final String COLUMN_NAME_STR5 = "str5";
    public static final String COLUMN_NAME_STR6 = "str6";
    public static final String COLUMN_NAME_STR7 = "str7";
    public static final String COLUMN_NAME_INT1 = "int1";
    public static final String COLUMN_NAME_INT2 = "int2";
    public static final String COLUMN_NAME_BOOLEAN1 = "boolean1";

    private static final long serialVersionUID = 1L;
    public String str1;
    public String str2;
    public String str3;
    public String str4;
    public String str5;
    public String str6;
    public String str7;
    public int int1;
    public int int2;
    public boolean boolean1;
}

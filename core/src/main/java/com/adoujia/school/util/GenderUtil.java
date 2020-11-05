package com.adoujia.school.util;

/**
 * @author fangcheng
 * @since 2020-11-06 00:38
 */
public class GenderUtil {
    private GenderUtil() {
    }

    public static final Integer MALE = 1;
    public static final Integer FEMALE = 2;
    public static final Integer UNKNOWN = 0;
    public static final String MALE_STRING = "男";
    public static final String FEMALE_STRING = "女";

    /**
     * get gender string by name
     *
     * @param genderString gender string
     * @return gender int
     */
    public static Integer getGender(String genderString) {
        if (genderString.contains(MALE_STRING)) {
            return MALE;
        }
        if (genderString.contains(FEMALE_STRING)) {
            return FEMALE;
        }
        return UNKNOWN;
    }
}

package com.zyc.common;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.zyc.utils.IDCardUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Assertion utility class that assists in validating arguments.
 *
 */
public abstract class Assert {
    private static String getMessage(String key, Object... args) {
        return Resources.getMessage(key, args);
    }

    /**  */
    public static void isTrue(boolean expression, String key) {
        if (!expression) {
            throw new IllegalArgumentException(getMessage(key));
        }
    }

    /**  */
    public static void isNull(Object object, String key) {
        if (object != null) {
            throw new IllegalArgumentException(getMessage(key));
        }
    }

    /**
     * (key_IS_NULL)
     */
    public static void notNull(Object object, String key, Object... args) {
        if (object == null) {
            throw new IllegalArgumentException(getMessage(key + "_IS_NULL", args));
        }
    }

    /**  */
    public static void hasLength(String text, String key) {
        if (StringUtils.isEmpty(text)) {
            throw new IllegalArgumentException(getMessage(key));
        }
    }

    /**  */
    public static void hasText(String text, String key) {
        if (StringUtils.isBlank(text)) {
            throw new IllegalArgumentException(getMessage(key));
        }
    }

    /**  */
    public static void doesNotContain(String textToSearch, String substring, String key) {
        if (StringUtils.isNotBlank(textToSearch) && StringUtils.isNotBlank(substring)
                && textToSearch.contains(substring)) {
            throw new IllegalArgumentException(getMessage(key));
        }
    }

    /** */
    public static void notEmpty(Object[] array, String key, Object... args) {
        if (ObjectUtils.isEmpty(array)) {
            throw new IllegalArgumentException(getMessage(key + "_IS_EMPTY", args));
        }
    }

    /**  */
    public static void noNullElements(Object[] array, String key) {
        if (array != null) {
            for (Object element : array) {
                if (element == null) {
                    throw new IllegalArgumentException(getMessage(key));
                }
            }
        }
    }

    /**  */
    public static void notEmpty(Collection<?> collection, String key) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new IllegalArgumentException(getMessage(key));
        }
    }

    /**  */
    public static void notEmpty(Map<?, ?> map, String key) {
        if (CollectionUtils.isEmpty(map)) {
            throw new IllegalArgumentException(getMessage(key));
        }
    }

    /**  */
    public static void isInstanceOf(Class<?> type, Object obj, String key) {
        notNull(type, key);
        if (!type.isInstance(obj)) {
            throw new IllegalArgumentException(getMessage(key));
        }
    }

    /**  */
    public static void isAssignable(Class<?> superType, Class<?> subType, String key) {
        notNull(superType, key);
        if (subType == null || !superType.isAssignableFrom(subType)) {
            throw new IllegalArgumentException(getMessage(key));
        }
    }

    /**
     * 空字符或NULL
     */
    public static void isBlank(String text, String key) {
        if (StringUtils.isNotBlank(text)) {
            throw new IllegalArgumentException(getMessage(key));
        }
    }

    /**
     * 非空字符串(key_IS_NULL)
     */
    public static void isNotBlank(String text, String key) {
        if (StringUtils.isBlank(text)) {
            throw new IllegalArgumentException(getMessage(key + "_IS_NULL"));
        }
    }

    /**
     * 允许最小值
     */
    public static void min(Integer value, Integer min, String key) {
        notNull(value, key);
        if (value < min) {
            throw new IllegalArgumentException(getMessage(key + "_MIN", min));
        }
    }

    /**
     * 允许最大值
     */
    public static void max(Integer value, Integer max, String key) {
        notNull(value, key);
        if (value > max) {
            throw new IllegalArgumentException(getMessage(key + "_MAX", max));
        }
    }

    /**
     * 允许值范围
     */
    public static void range(Integer value, Integer min, Integer max, String key) {
        min(value, min, key);
        max(value, max, key);
    }

    /**
     * 允许最小值
     */
    public static void min(Float value, Float min, String key) {
        notNull(value, key);
        if (value < min) {
            throw new IllegalArgumentException(getMessage(key + "_MIN", min));
        }
    }

    /**
     * 允许最大值
     */
    public static void max(Float value, Float max, String key) {
        notNull(value, key);
        if (value > max) {
            throw new IllegalArgumentException(getMessage(key + "_MAX", max));
        }
    }

    /**
     * 允许值范围
     */
    public static void range(Float value, Float min, Float max, String key) {
        min(value, min, key);
        max(value, max, key);
    }

    /**
     * 允许最小值
     */
    public static void min(Double value, Double min, String key) {
        notNull(value, key);
        if (value < min) {
            throw new IllegalArgumentException(getMessage(key + "_MIN", min));
        }
    }

    /**
     * 允许最大值
     */
    public static void max(Double value, Double max, String key) {
        notNull(value, key);
        if (value > max) {
            throw new IllegalArgumentException(getMessage(key + "_MAX", max));
        }
    }

    /**
     * 允许值范围
     */
    public static void range(Double value, Double min, Double max, String key) {
        min(value, min, key);
        max(value, max, key);
    }

    /**
     * 字符长度(key_LENGTH)
     */
    public static void length(String text, Integer min, Integer max, String key) {
        notNull(text, key);
        if (min != null && text.length() < min) {
            throw new IllegalArgumentException(getMessage(key + "_LENGTH", min, max));
        }
        if (max != null && text.length() > max) {
            throw new IllegalArgumentException(getMessage(key + "_LENGTH", min, max));
        }
    }

    /**
     * 未来某一天
     */
    public static void future(Date date, String key) {
        if (date != null && date.compareTo(new Date()) <= 0) {
            throw new IllegalArgumentException(getMessage(key + "_NOT_FUTURE"));
        }
    }

    /**
     * 身份证
     */
    public static void idCard(String text) {
        if (!IDCardUtil.isIdentity(text)) {
            throw new IllegalArgumentException(getMessage("IDCARD_ILLEGAL"));
        }
    }

    /**
     * 邮箱
     */
    public static void email(String text) {
        String regex = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        pattern(text, regex, true, "EMAIL");
    }

    /**
     * 手机号
     */
    public static void mobile(String text) {
        String regex = "((^(13|15|17|18)[0-9]{9}$)|(^0[1,2]{1}\\d{1}-?\\d{8}$)|(^0[3-9] {1}\\d{2}-?\\d{7,8}$)|(^0[1,2]{1}\\d{1}-?\\d{8}-(\\d{1,4})$)|(^0[3-9]{1}\\d{2}-? \\d{7,8}-(\\d{1,4})$))";
        pattern(text, regex, true, "MOBILE");
    }

    /**
     * 正则表达式
     */
    public static void pattern(String text, String regex, boolean flag, String key) {
        boolean result = false;
        try {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(text);
            result = matcher.matches();
        } catch (Exception e) {
            result = false;
        }
        if (result != flag) {
            throw new IllegalArgumentException(getMessage(key + "_ILLEGAL"));
        }
    }


    /**
     * 邮箱
     */
    public static String password(String password) {
        password = password.trim();
        pattern(password, "(?![0-9]+)([A-Za-z0-9]{8,16})", true, "PASSWORD");
        return password;
    }

    public static String userName(String userName) {
        userName = userName.trim();
        if (userName.length() > 7) {
            throw new IllegalArgumentException(getMessage("USER_NAME_ILLEGAL"));
        }
        return userName;
    }

    public static String userInfo(String userInfo) {
        userInfo = userInfo.trim();
        if (userInfo.length() > 150) {
            throw new IllegalArgumentException(getMessage("USER_INFO_ILLEGAL"));
        }
        return userInfo;
    }

    public static String payPassword(String password) {
        password = password.trim();
        pattern(password, "[0-9]{6}", true, "PAY_PASSWORD");
        return password;
    }

    public static void confirmTheCurrentRole(Integer currentRole, Integer actualRole) {
        if (!currentRole.equals(actualRole)) {
            throw new IllegalArgumentException(getMessage("INCONSISTENT_CURRENT_ROLE"));
        }
    }


    /**
     * 手机号
     */
    public static String phone(String phone) {
        Assert.isNotBlank(phone, "PHONE");
        phone = phone.trim();
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        Phonenumber.PhoneNumber phoneNumber = new Phonenumber.PhoneNumber();
        phoneNumber.setCountryCode(86);
        phoneNumber.setNationalNumber(Long.valueOf(phone));

        Assert.isTrue(phoneNumberUtil.isValidNumber(phoneNumber), "PHONE_FORMAT_ERROR");

        return phone;
    }

    /**
     * 身份证号码验证 1、号码的结构 公民身份号码是特征组合码，由十七位数字本体码和一位校验码组成。排列顺序从左至右依次为：六位数字地址码，
     * 八位数字出生日期码，三位数字顺序码和一位数字校验码。 2、地址码(前六位数）
     * 表示编码对象常住户口所在县(市、旗、区)的行政区划代码，按GB/T2260的规定执行。 3、出生日期码（第七位至十四位）
     * 表示编码对象出生的年、月、日，按GB/T7408的规定执行，年、月、日代码之间不用分隔符。 4、顺序码（第十五位至十七位）
     * 表示在同一地址码所标识的区域范围内，对同年、同月、同日出生的人编定的顺序号， 顺序码的奇数分配给男性，偶数分配给女性。 5、校验码（第十八位数）
     * （1）十七位数字本体码加权求和公式 S = Sum(Ai * Wi), i = 0, ... , 16 ，先对前17位数字的权求和
     * Ai:表示第i位置上的身份证号码数字值 Wi:表示第i位置上的加权因子 Wi: 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4
     * 2 （2）计算模 Y = mod(S, 11) （3）通过模得到对应的校验码 Y: 0 1 2 3 4 5 6 7 8 9 10 校验码: 1 0
     * X 9 8 7 6 5 4 3 2
     */

    /**
     * 功能：身份证的有效验证
     *
     * @param idCard 身份证号
     * @return 有效：返回"" 无效：返回String信息
     */
    public static String validateIDCard(String idCard) {
        idCard = idCard.trim();

        validateIDCardLength(idCard);

        validateIDCardDate(idCard);

        validateIDCardAreaCode(idCard);

        validateIDCardVerifyCode(idCard);

        return idCard;
    }

    private static void validateIDCardLength(String idCard) {
        // ================ 号码的长度 15位或18位 ================
        if (idCard.length() != 15 && idCard.length() != 18) {
            idCardInvalid();
        }
    }

    private static void validateIDCardDate(String idCard) {
        String dateStr;
        if (idCard.length() == 15) {
            dateStr = idCard.substring(0, 6) + "19" + idCard.substring(6, 15);
        } else {
            dateStr = idCard.substring(0, 17);
        }
        try {
            LocalDate.of(
                    Integer.valueOf(dateStr.substring(6, 10)),
                    Integer.valueOf(dateStr.substring(10, 12)),
                    Integer.valueOf(dateStr.substring(12, 14))
            );
        } catch (Exception e) {
            idCardInvalid();
        }
    }

    private static void validateIDCardAreaCode(String idCard) {
        Map<String, String> areaMap = areaCode();
        if (areaMap.get(idCard.substring(0, 2)) == null) {
            // 身份证地区编码错误
            idCardInvalid();
        }
    }

    private static void validateIDCardVerifyCode(String idCard) {
        if (Objects.equals(15, idCard.length())) {
            return;
        }
        String[] ValCodeArr = {"1", "0", "X", "9", "8", "7", "6", "5", "4",
                "3", "2"};
        String[] Wi = {"7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
                "9", "10", "5", "8", "4", "2"};
        // ================ 判断最后一位的值 ================
        int TotalmulAiWi = 0;
        for (int i = 0; i < 17; i++) {
            TotalmulAiWi = TotalmulAiWi
                    + Integer.parseInt(String.valueOf(idCard.charAt(i)))
                    * Integer.parseInt(Wi[i]);
        }
        int modValue = TotalmulAiWi % 11;
        // 大写的罗马数字表示10
        if (!Objects.equals(String.valueOf(idCard.charAt(17)).toUpperCase(), ValCodeArr[modValue])) {
            // 身份证无效，不是合法的身份证号码
            idCardInvalid();
        }
    }

    /**
     * 功能：设置地区编码
     *
     * @return Hashtable 对象
     */
    private static Map<String, String> areaCode() {
        Map<String, String> areaMap = new HashMap<>();
        areaMap.put("11", "北京");
        areaMap.put("12", "天津");
        areaMap.put("13", "河北");
        areaMap.put("14", "山西");
        areaMap.put("15", "内蒙古");
        areaMap.put("21", "辽宁");
        areaMap.put("22", "吉林");
        areaMap.put("23", "黑龙江");
        areaMap.put("31", "上海");
        areaMap.put("32", "江苏");
        areaMap.put("33", "浙江");
        areaMap.put("34", "安徽");
        areaMap.put("35", "福建");
        areaMap.put("36", "江西");
        areaMap.put("37", "山东");
        areaMap.put("41", "河南");
        areaMap.put("42", "湖北");
        areaMap.put("43", "湖南");
        areaMap.put("44", "广东");
        areaMap.put("45", "广西");
        areaMap.put("46", "海南");
        areaMap.put("50", "重庆");
        areaMap.put("51", "四川");
        areaMap.put("52", "贵州");
        areaMap.put("53", "云南");
        areaMap.put("54", "西藏");
        areaMap.put("61", "陕西");
        areaMap.put("62", "甘肃");
        areaMap.put("63", "青海");
        areaMap.put("64", "宁夏");
        areaMap.put("65", "新疆");
        areaMap.put("71", "台湾");
        areaMap.put("81", "香港");
        areaMap.put("82", "澳门");
        areaMap.put("91", "国外");
        return areaMap;
    }

    private static void idCardInvalid() {
        throw new IllegalArgumentException(getMessage("INVALID_ID_CARD"));
    }


    public static void verification(Map verification) {
        if (verification.size() == 1) {
            throw new IllegalArgumentException(getMessage("VERIFICATION_IS_ERROR"));
        }
    }


    public static void birthDay(String birthDay) {
        try {
            LocalDate.parse(birthDay);
        } catch (Exception e) {
            throw new IllegalArgumentException(getMessage("BIRTHDAY_ILLEGAL"));
        }
    }

    public static String nickName(String userName) {
        userName = userName.trim();
        if (userName.length() > 7) {
            throw new IllegalArgumentException(getMessage("NICK_NAME_ILLEGAL"));
        }
        return userName;
    }

}

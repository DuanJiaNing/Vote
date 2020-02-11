package com.duan.vote.enums;

/**
 * Created on 2020/01/10.
 *
 * @author DuanJiaNing
 */
public enum Vote {

    AGREE(1),
    DISAGREE(-1);
    private final int code;

    Vote(int code) {
        this.code = code;
    }

    public static Vote valueOf(Integer code) {
        if (code == null) {
            return null;
        }

        for (Vote value : Vote.values()) {
            if (value.code == code) {
                return value;
            }
        }

        return null;
    }

    public int getCode() {
        return code;
    }
}

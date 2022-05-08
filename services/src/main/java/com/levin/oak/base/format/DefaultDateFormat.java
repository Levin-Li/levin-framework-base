package com.levin.oak.base.format;

import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.databind.util.StdDateFormat;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;

public class DefaultDateFormat extends StdDateFormat {
    @Override
    protected Date _parseDate(String dateStr, ParsePosition pos) throws ParseException {

        Date date = super._parseDate(dateStr, pos);

        if (date == null) {
            return DateUtil.parse(dateStr).toJdkDate();
        }
        return null;
    }
}

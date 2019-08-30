package com.aoke.apartmentsystem.common.converter;

import com.aoke.apartmentsystem.common.utils.DateUtil;
import com.wuwenze.poi.convert.WriteConverter;
import com.wuwenze.poi.exception.ExcelKitWriteConverterException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;

/**
 * Execl导出时间类型字段格式化
 *
 * @author xiaoxinglin
 */
@Slf4j
public class TimeConverter implements WriteConverter {

    Logger log = LoggerFactory.getLogger(TimeConverter.class);
    @Override
    public String convert(Object value) {
        if (value == null)
            return "";
        else {
            try {
                return DateUtil.formatCSTTime(value.toString(), DateUtil.FULL_TIME_SPLIT_PATTERN);
            } catch (ParseException e) {
                String message = "时间转换异常";
                log.error(message, e);
                throw new ExcelKitWriteConverterException(message);
            }
        }
    }
}

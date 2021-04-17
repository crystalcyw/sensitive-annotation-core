package com.zzyk.sensitive.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.zzyk.sensitive.annotation.SensitiveField;
import com.zzyk.sensitive.constant.SensitiveConstant;
import com.zzyk.sensitive.enums.SensitiveType;
import com.zzyk.sensitive.util.HttpUtils;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;

/**
 * 自定义脱敏字段 json 序列化
 *
 * @author liam
 * @date 2021/04/13
 */
public class SensitiveFieldSerialize extends JsonSerializer<String> implements ContextualSerializer {

    private String pattern;

    private String targetChar;

    public SensitiveFieldSerialize() {
    }

    public SensitiveFieldSerialize(final String pattern, final String targetChar) {
        this.pattern = pattern;
        this.targetChar = targetChar;
    }

    public SensitiveFieldSerialize(final SensitiveType sensitiveType) {
        this.pattern = sensitiveType.getPattern();
        this.targetChar = sensitiveType.getTargetChar();
    }

    /**
     * 重写序列化逻辑
     *
     * @param value
     * @param gen
     * @param serializers
     * @throws IOException
     */
    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException {
        HttpServletRequest request = HttpUtils.currentRequest();
        Object isSensitive = request.getAttribute(SensitiveConstant.IS_SENSITIVE);
        if (Boolean.TRUE.equals(isSensitive)) {
            value = value.replaceAll(this.pattern, this.targetChar);
        }

        gen.writeString(value);
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property)
        throws JsonMappingException {
        // 为空直接跳过
        if (property != null) {
            // 只处理 string 类脱敏
            if (Objects.equals(property.getType().getRawClass(), String.class)) {
                SensitiveField sensitiveField = property.getAnnotation(SensitiveField.class);
                if (sensitiveField == null) {
                    sensitiveField = property.getContextAnnotation(SensitiveField.class);
                }

                // 如果 SensitiveField 不为空
                if (sensitiveField != null) {
                    SensitiveFieldSerialize sensitiveFieldSerialize;
                    if (StringUtils.isEmpty(sensitiveField.pattern())) {
                        sensitiveFieldSerialize = new SensitiveFieldSerialize(sensitiveField.value());
                    } else {
                        sensitiveFieldSerialize = new SensitiveFieldSerialize(sensitiveField.pattern(), sensitiveField.targetChar());
                    }

                    return sensitiveFieldSerialize;
                }
            }
            // 其他类直接跳过
            prov.findValueSerializer(property.getType(), property);
        }

        return prov.findNullValueSerializer(property);
    }
}

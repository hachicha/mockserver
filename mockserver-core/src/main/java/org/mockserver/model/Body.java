package org.mockserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.nio.charset.Charset;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author jamesdbloom
 */
public abstract class Body<T> extends Not {

    private final Type type;

    public Body(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public abstract T getValue();

    @JsonIgnore
    public byte[] getRawBytes() {
        return toString().getBytes(UTF_8);
    }

    @JsonIgnore
    public Charset getCharset(Charset defaultIfNotSet) {
        if (this instanceof BodyWithContentType) {
            return this.getCharset(defaultIfNotSet);
        }
        return defaultIfNotSet;
    }

    public String getContentType() {
        if (this instanceof BodyWithContentType) {
            return this.getContentType();
        }
        return null;
    }

    public enum Type {
        BINARY,
        JSON,
        JSON_SCHEMA,
        JSON_PATH,
        PARAMETERS,
        REGEX,
        STRING,
        XML,
        XML_SCHEMA,
        XPATH,
        LOG_EVENT,
    }
}

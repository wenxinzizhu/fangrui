package com.hbfangrui.base.ddd.domain.model.support;

import com.google.common.base.Preconditions;
import com.hbfangrui.base.ddd.domain.model.Identifier;
import com.hbfangrui.base.ddd.domain.model.IdentifierGenerator;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by taoli on 15/10/31.
 */
public abstract class StringBasedIdentifierGenerator<ID extends Identifier> implements IdentifierGenerator<ID>{
    private final String type;
    private final int maxLength;

    public StringBasedIdentifierGenerator(String type,
                                          int maxLength) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(type));
        Preconditions.checkArgument(maxLength > 0);
        this.type = type;
        this.maxLength = maxLength;
    }

    @Override
    public ID get() {
        StringBuilder stringBuilder = new StringBuilder(type);
        stringBuilder.append(":");
        stringBuilder.append(formatDate());
        stringBuilder.append(":");
        stringBuilder.append(uuid());
        int length = stringBuilder.length();
        String id = stringBuilder.substring(0, Math.min(length, this.maxLength));
        return buildIdentifier(id);
    }

    protected abstract ID buildIdentifier(String id);

    private String uuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    private String formatDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(new Date());
    }
}

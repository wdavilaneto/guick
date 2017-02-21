/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.commons.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

/**
 * Created by igor.custodio on 03/11/2014.
 * Formato Brasileiro em casa decimal
 */
public class CustomBigDecimalDeserializer extends JsonDeserializer<BigDecimal> {

    @Override
    public BigDecimal deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);

        if (StringUtils.isBlank(node.asText())){
            return null;
        }

        DecimalFormat df =new DecimalFormat();
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        df.setParseBigDecimal(true);
        dfs.setDecimalSeparator(',');
        df.setGroupingUsed(false);
        df.setDecimalFormatSymbols(dfs);
        df.setMaximumFractionDigits(32);
        df.setMaximumIntegerDigits(32);

        try {
            return (BigDecimal)df.parse(node.asText());
        } catch (ParseException e) {
            throw new IOException(e);
        }
    }

    @Override
    public Class<BigDecimal> handledType() {
        return BigDecimal.class;
    }
}
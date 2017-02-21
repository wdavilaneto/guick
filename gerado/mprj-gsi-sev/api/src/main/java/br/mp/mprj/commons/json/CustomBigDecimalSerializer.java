/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.commons.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * Created by igor.custodio on 03/11/2014.
 * Formato Brasileiro em casa decimal
 */
public class CustomBigDecimalSerializer extends JsonSerializer<BigDecimal> {

    @Override
    public void serialize(BigDecimal value, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {

        if (value != null) {

            DecimalFormat df = new DecimalFormat();
            DecimalFormatSymbols dfs = new DecimalFormatSymbols();
            df.setParseBigDecimal(true);
            dfs.setDecimalSeparator(',');
            df.setGroupingUsed(false);
            df.setDecimalFormatSymbols(dfs);
            df.setMaximumFractionDigits(32);
            df.setMaximumIntegerDigits(32);

            jsonGenerator.writeString(df.format(value.doubleValue()));

        } else {
            jsonGenerator.writeString("");
        }
    }

    @Override
    public Class<BigDecimal> handledType() {
        return BigDecimal.class;
    }
}
/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.commons.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.lang3.time.FastDateFormat;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by igor.custodio on 03/11/2014.
 * Data com hora para formato JSON - resolve problema com DatePicker angular
 */
public class JsonDateDeserializer extends JsonDeserializer<Date> {

    private static final FastDateFormat dateFormat = FastDateFormat.getInstance("dd/MM/yyyy");
    private static final FastDateFormat dateFormat2 = FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss.sss'Z'");

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);

        try {
            return (Date)dateFormat.parse(node.asText());
        } catch (ParseException e) {
            try {
                return (Date)dateFormat2.parse(node.asText());
            } catch (ParseException e1) {
                throw new IOException(e);
            }

        }
    }
}
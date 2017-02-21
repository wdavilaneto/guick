/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.commons.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.lang3.time.FastDateFormat;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by robson.machado on 20/02/2015.
 */
public class JsonDateListSerializer extends JsonSerializer<List<Date>> {

    private static final FastDateFormat dateFormat = FastDateFormat.getInstance("dd/MM/yyyy");

    @Override
    public void serialize(List<Date> dates, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartArray();
        for (Date date : dates){
            String formattedDate = dateFormat.format(date);
            jsonGenerator.writeString(formattedDate);
        }
        jsonGenerator.writeEndArray();
    }
}

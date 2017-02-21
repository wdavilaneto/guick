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
import org.apache.commons.lang.time.FastDateFormat;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

/**
 * Used to serialize Java.util.Date, which is not a common JSON
 * type, so we have to create a custom serialize method;.
 * Data com hora para formato JSON - resolve problema com DatePicker angular
 */
@Component
public class JsonDateSerializer extends JsonSerializer<Date> {

    private static final FastDateFormat dateFormat = FastDateFormat.getInstance("dd/MM/yyyy");

    @Override
    public void serialize(Date date, JsonGenerator gen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        String formattedDate = dateFormat.format(date);

        gen.writeString(formattedDate);
    }

}


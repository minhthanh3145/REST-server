package evchargingstore.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DateUtil {
	
	public static SimpleDateFormat getDateFormat() {
		return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
	}

	public static String format(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(date);
	}

	public static Date format(String string) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse(string);
		} catch (ParseException e) {
			return null;
		}
	}

	public static class CustomDateDeserializer extends JsonDeserializer<Date> {
		@Override
		public Date deserialize(JsonParser jsonParser, DeserializationContext arg1)
				throws IOException, JsonProcessingException {
			String date = jsonParser.getText();
			return DateUtil.format(date);
		}
	}

	public static class CustomDateSerializer extends JsonSerializer<Date> {
		@Override
		public void serialize(Date date, JsonGenerator generator, SerializerProvider provider) throws IOException {
			if (date == null) {
				generator.writeNull();
			} else {
				generator.writeString(DateUtil.format(date));
			}
		}

	}
}

package br.com.depro.fw.typezero.infrastructure.json.helper;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomDateDeserializer extends JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonParser jsonparser, DeserializationContext context) throws IOException, JsonProcessingException {
		String dateAsString = jsonparser.getText();
		try {
			return CustomDateSerializer.FORMATTER.parse(dateAsString);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}
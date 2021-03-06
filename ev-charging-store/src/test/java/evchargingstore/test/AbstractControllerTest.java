package evchargingstore.test;

import java.io.IOException;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import evchargingstore.ApplicationMain;
import evchargingstore.facade.ChargingSessionServiceFacade;
import evchargingstore.repository.ChargingSessionRepository;
import evchargingstore.util.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationMain.class)
@WebAppConfiguration
public class AbstractControllerTest {

	protected MockMvc mvc;

	protected ObjectMapper objectMapper;

	@Autowired
	WebApplicationContext webApplicationContext;
	

	@Before
	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(DateUtil.getDateFormat());
	}

	protected String mapToJson(Object obj) throws JsonProcessingException {
		return objectMapper.writeValueAsString(obj);
	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {
		return objectMapper.readValue(json, clazz);
	}

}

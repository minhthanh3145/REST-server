package evchargingstore.test;

import java.util.Date;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import com.fasterxml.jackson.core.JsonProcessingException;

import evchargingstore.entity.ChargingSession;
import evchargingstore.entity.ChargingSessionResponse;
import evchargingstore.entity.ChargingSessionStatisticsResponse;
import evchargingstore.entity.ChargingSessionStatus;

public class ChargingSessionControllerTest extends AbstractControllerTest {
	
	private Date now = new Date();
	
	public String submitChargingSession() throws JsonProcessingException, Exception {
		String endpoint = "/chargingSessions";
		
		ChargingSession bodyRequest = new ChargingSession();
		bodyRequest.setStartedAt(now);
		
		ChargingSessionResponse expectedResponse = new ChargingSessionResponse();
		expectedResponse.setStartedAt(now);
		expectedResponse.setStatus(ChargingSessionStatus.STARTED);
		
		
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(endpoint)
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8")
				.content(mapToJson(bodyRequest))
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
		ChargingSessionResponse actualResponse = mapFromJson(mvcResult.getResponse().getContentAsString()
				, ChargingSessionResponse.class);
		
		Assert.assertEquals(expectedResponse.getStartedAt(), actualResponse.getStartedAt());
		Assert.assertEquals(expectedResponse.getStatus(), ChargingSessionStatus.STARTED);
		return actualResponse.getId();
	}
	
	public void suspendChargingSession(String uuid) throws JsonProcessingException, Exception {
		String endpoint = "/chargingSessions";
		
		ChargingSession bodyRequest = new ChargingSession();
		bodyRequest.setSuspendedAt(now);
		
		ChargingSessionResponse expectedResponse = new ChargingSessionResponse();
		expectedResponse.setId(uuid);
		expectedResponse.setStartedAt(now);
		expectedResponse.setSuspendedAt(now);
		expectedResponse.setStatus(ChargingSessionStatus.SUSPENDED);
						
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.put(endpoint + "/" + uuid)
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8")
				.content(mapToJson(bodyRequest))
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
		
		ChargingSessionResponse actualResponse = mapFromJson(mvcResult.getResponse().getContentAsString()
				, ChargingSessionResponse.class);
		
		Assert.assertEquals(expectedResponse.getId(), actualResponse.getId());
		Assert.assertEquals(expectedResponse.getStartedAt(), actualResponse.getStartedAt());
		Assert.assertEquals(expectedResponse.getSuspendedAt(), actualResponse.getStartedAt());
		Assert.assertEquals(expectedResponse.getStatus(), ChargingSessionStatus.SUSPENDED);
	}
	
	public void getChargingSession(String uuid) throws JsonProcessingException, Exception {
		String endpoint = "/chargingSessions";
		
		ChargingSessionResponse expectedResponse = new ChargingSessionResponse();
		expectedResponse.setId(uuid);
		expectedResponse.setStartedAt(now);
		expectedResponse.setSuspendedAt(now);
		expectedResponse.setStatus(ChargingSessionStatus.SUSPENDED);
						
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.get(endpoint + "/" + uuid)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
		
		ChargingSessionResponse actualResponse = mapFromJson(mvcResult.getResponse().getContentAsString()
				, ChargingSessionResponse.class);
		
		Assert.assertEquals(expectedResponse.getId(), actualResponse.getId());
		Assert.assertEquals(expectedResponse.getStartedAt(), actualResponse.getStartedAt());
		Assert.assertEquals(expectedResponse.getSuspendedAt(), actualResponse.getStartedAt());
		Assert.assertEquals(expectedResponse.getStatus(), ChargingSessionStatus.SUSPENDED);
	}
	
	public void getChargingSessionStatistics() throws JsonProcessingException, Exception {
		String endpoint = "/chargingSummary";
		
		ChargingSessionStatisticsResponse expectedResponse = new ChargingSessionStatisticsResponse(1, 1);
						
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.get(endpoint)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
		
		ChargingSessionStatisticsResponse actualResponse = mapFromJson(mvcResult.getResponse().getContentAsString()
				, ChargingSessionStatisticsResponse.class);
		
		Assert.assertEquals(expectedResponse.getStartedCount(), actualResponse.getStartedCount());
		Assert.assertEquals(expectedResponse.getSuspendedCount(), actualResponse.getSuspendedCount());
	}
	
	@Test
	public void testControllerForAllOperations() throws JsonProcessingException, Exception {
		String submittedId = submitChargingSession();
		suspendChargingSession(submittedId);
		getChargingSession(submittedId);
		getChargingSessionStatistics();
	}
}

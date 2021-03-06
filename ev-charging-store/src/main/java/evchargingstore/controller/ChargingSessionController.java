package evchargingstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import evchargingstore.entity.ChargingSession;
import evchargingstore.entity.ChargingSessionResponse;
import evchargingstore.entity.ChargingSessionStatisticsResponse;
import evchargingstore.facade.ChargingSessionServiceFacade;

@RestController
public class ChargingSessionController {

	@Autowired
	private ChargingSessionServiceFacade chargingSessionServiceFacade;

	@RequestMapping(value = "/chargingSessions", method = RequestMethod.POST)
	public ChargingSessionResponse submitChargingSession(@RequestBody ChargingSession session) {
		return chargingSessionServiceFacade.submitChargingSession(session);
	}

	@RequestMapping(value = "/chargingSessions/{id}", method = RequestMethod.PUT)
	public ChargingSessionResponse suspendChargingSession(@PathVariable(value = "id") String id,
			@RequestBody ChargingSession session) {
		return chargingSessionServiceFacade.suspendChargingSession(id, session);
	}
	
	@RequestMapping(value = "/chargingSessions/{id}", method = RequestMethod.GET)
	public ChargingSessionResponse getChargingSession(@PathVariable(value = "id") String id) {
		return chargingSessionServiceFacade.getChargingSession(id);
	}
	
	@RequestMapping(value = "/chargingSummary", method = RequestMethod.GET)
	public ChargingSessionStatisticsResponse getChargingSessionStatistics() {
		return chargingSessionServiceFacade.getChargingSessionStatistics();
	}
}

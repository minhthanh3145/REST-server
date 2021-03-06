package evchargingstore.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import evchargingstore.util.DateUtil.CustomDateDeserializer;
import evchargingstore.util.DateUtil.CustomDateSerializer;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChargingSessionResponse {

	private String id;
	private Date startedAt;
	private Date suspendedAt;
	private ChargingSessionStatus status;
	private String message;

	public ChargingSessionResponse(ChargingSession info) {
		this.id = info.getId();
		this.startedAt = info.getStartedAt();
		this.suspendedAt = info.getSuspendedAt();
		this.status = info.getStatus();
	}

	public ChargingSessionResponse(String message) {
		this.message = message;
	}

	public ChargingSessionResponse() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonDeserialize(using = CustomDateDeserializer.class)
	public void setStartedAt(Date at) {
		this.startedAt = at;
	}

	@JsonDeserialize(using = CustomDateDeserializer.class)
	public void setSuspendedAt(Date at) {
		this.suspendedAt = at;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getStartedAt() {
		return this.startedAt;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getSuspendedAt() {
		return this.suspendedAt;
	}

	public ChargingSessionStatus getStatus() {
		return this.status;
	}

	public void setStatus(ChargingSessionStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

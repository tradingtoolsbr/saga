package br.com.trandingtools.common.event;

import java.util.Date;
import java.util.UUID;

public interface Event {
	
	UUID getEventID();
	Date getEventDate();
	

}

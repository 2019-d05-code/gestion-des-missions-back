package dev.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MissionNonTrouveeException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(MissionNonTrouveeException.class);
	
	public MissionNonTrouveeException(String message) {
		super(message);
		LOG.error(message);
	}
}

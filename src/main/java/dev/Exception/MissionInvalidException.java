package dev.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MissionInvalidException extends RuntimeException {
	private static final Logger LOG = LoggerFactory.getLogger(MissionInvalidException.class);

	public MissionInvalidException(String message) {
		LOG.error(message);
	}

}

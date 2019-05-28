package dev.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModificationInvalideException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(MissionInvalidException.class);

	public ModificationInvalideException(String message) {
		super(message);
		LOG.error(message);
	}

}

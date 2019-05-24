package dev.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CollegueNonTrouve extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(MissionInvalidException.class);
	
	public CollegueNonTrouve(String message) {
		LOG.error(message);
	}

}

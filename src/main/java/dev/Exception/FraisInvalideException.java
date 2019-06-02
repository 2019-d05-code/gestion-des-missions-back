package dev.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FraisInvalideException extends RuntimeException {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(NatureInvalideException.class);

    public FraisInvalideException() {}

    public FraisInvalideException(String msg)
    {
	super(msg);
	LOG.error(msg);

    }
}

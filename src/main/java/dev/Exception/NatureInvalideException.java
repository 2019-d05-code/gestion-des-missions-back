package dev.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NatureInvalideException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(NatureInvalideException.class);

    public NatureInvalideException (String msg)
    {
	super (msg);
	LOG.error (msg);
    }

}

package dev.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {
    // la méthode handleConflict est exécutée lorsqu'un contrôleur émet une
    // exception présente
    // dans la liste définie par l'annotation @ExceptionHandler
    /*
     * 2xx=success 3xx=Redirection 4xx=error client-> { bad request ->404 (the
     * most common)} 5xx=error server
     *
     */
    @ExceptionHandler(value = { MissionInvalidException.class })
    protected ResponseEntity<Object> handleConflictMission(RuntimeException ex, WebRequest request) {
	String bodyOfResponse = "La mission est invalide : " + ex.getMessage ();
	return ResponseEntity.status(404).body(bodyOfResponse);
    }

    @ExceptionHandler(value = { NatureInvalideException.class })
    protected ResponseEntity<Object> handleConflictNature(RuntimeException ex, WebRequest request) {
	String bodyOfResponse = "La nature est invalide : " + ex.getMessage ();
	return ResponseEntity.status(404).body(bodyOfResponse);
    }

    @ExceptionHandler(value = { CollegueNonTrouveException.class })
    protected ResponseEntity<Object> conflictHandleCollegue(RuntimeException ex, WebRequest request) {
	String bodyOfResponse = "Collegue n'existe pas : " + ex.getMessage();
	return ResponseEntity.status(404).body(bodyOfResponse);
    }
}

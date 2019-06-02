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

    @ExceptionHandler(value = { MissionNonTrouveeException.class })
    protected ResponseEntity<Object> conflictHandleMissionNonTrouvee (RuntimeException ex, WebRequest request) {
	String bodyOfResponse = "La mission n'existe pas : " + ex.getMessage();
	return ResponseEntity.status(404).body(bodyOfResponse);
    }

    @ExceptionHandler(value = { NatureInvalideException.class })
    protected ResponseEntity<Object> handleConflictNature(RuntimeException ex, WebRequest request) {
	String bodyOfResponse = "La nature est invalide : " + ex.getMessage ();
	return ResponseEntity.status(404).body(bodyOfResponse);
    }

    @ExceptionHandler(value = { ModificationInvalideException.class })
    protected ResponseEntity<Object> handleConflictModification(RuntimeException ex, WebRequest request) {
	String bodyOfResponse = "La modification est invalide : " + ex.getMessage ();
	return ResponseEntity.status(404).body(bodyOfResponse);
    }

    @ExceptionHandler(value = { FraisInvalideException.class })
    protected ResponseEntity<Object> conflictHandleFrais (RuntimeException ex, WebRequest request) {
	String bodyOfResponse = "Frais est invalide : " + ex.getMessage();
	return ResponseEntity.status(404).body(bodyOfResponse);
    }

    @ExceptionHandler(value = { CollegueNonTrouveException.class })
    protected ResponseEntity<Object> conflictHandleCollegue(RuntimeException ex, WebRequest request) {
	String bodyOfResponse = "Collegue n'existe pas : " + ex.getMessage();
	return ResponseEntity.status(404).body(bodyOfResponse);
    }

    @ExceptionHandler(value = { NullPointerException.class })
    protected ResponseEntity<Object> conflictHandleNull(RuntimeException ex, WebRequest request) {
	String bodyOfResponse = "Une valeur n'a pas correctement été saisie !";
	return ResponseEntity.status(404).body(bodyOfResponse);
    }
}

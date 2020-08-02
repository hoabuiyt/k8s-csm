package com.bank.csm.constants;

import org.springframework.http.HttpStatus;

/**
 * This enumeration contains the list of exceptions
 * @author kumar-sand
 *
 */
public enum Exceptions {
	
	E0000(HttpStatus.CONFLICT, "0000", "exception.0000", "Generic exeption - unknown"),
	E0001(HttpStatus.CONFLICT, "0001", "exception.0001", "Illegal State Exception occurred"),
	E0002(HttpStatus.BAD_REQUEST, "0002", "exception.0002", "{0} parameter is missing"),
	E0003(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "0003", "exception.0003", "{0} media type is not supported. Supported media types are "),
	E0004(HttpStatus.BAD_REQUEST, "0004", "exception.0004", "Validation failed"),
	E0005(HttpStatus.BAD_REQUEST, "0005", "exception.0005", "Constraint violation occurred"),
	E0006(HttpStatus.INTERNAL_SERVER_ERROR, "0006", "exception.0006", "Data access exception"),
	E0007(HttpStatus.INTERNAL_SERVER_ERROR, "0007", "exception.0007", "Persistence exception"),
	E0008(HttpStatus.INTERNAL_SERVER_ERROR, "0008", "exception.0008", "SQL grammer exception"),
	E0009(HttpStatus.BAD_REQUEST, "0009", "exception.0009", "Http message not readable exception"),
	E0010(HttpStatus.INTERNAL_SERVER_ERROR, "0010", "exception.0010", "Http message not writeable exception"),
	E0011(HttpStatus.NOT_FOUND, "0011", "exception.0011", "Record not found"),
	E0012(HttpStatus.CONFLICT, "0012", "exception.0012", "Property value exception"),
	E0013(HttpStatus.INTERNAL_SERVER_ERROR, "0013", "exception.0013", "Data integrity violation exception"),
	E0014(HttpStatus.BAD_REQUEST, "0014", "exception.0014", "Method argument type mismatch exception"),
	E0015(HttpStatus.NOT_FOUND, "0015", "exception.0015", "No handler found exception"),
	E0016(HttpStatus.METHOD_NOT_ALLOWED, "0016", "exception.0016", "{0} method is not supported for this request. Supported methods are "),
	E0017(HttpStatus.BAD_REQUEST, "0017", "exception.0017", "Number format exception"),
	E0018(HttpStatus.BAD_REQUEST, "0018", "exception.0018", "Parse exception"),
	E0019(HttpStatus.BAD_REQUEST, "0019", "exception.0019", "Illegal argument exception"),
	E0020(HttpStatus.NOT_FOUND, "0020", "exception.0020", "Resource not found exception"),
	E0021(HttpStatus.INTERNAL_SERVER_ERROR, "0021", "exception.0021", "Global Runtime Exception"),
	E0022(HttpStatus.INTERNAL_SERVER_ERROR, "0022", "exception.0022", "Global Null pointer exception"),
	E0023(HttpStatus.INTERNAL_SERVER_ERROR, "0023", "exception.0023", "Global exception"),
	E0024(HttpStatus.BAD_REQUEST, "0024", "exception.0024", "Empty Request"),
	E0025(HttpStatus.BAD_REQUEST, "0025", "exception.0025", "Invalid input Request"),
	
	E0026(HttpStatus.BAD_REQUEST, "0026", "exception.0026", "Branch is already available"),
	E0027(HttpStatus.BAD_REQUEST, "0027", "exception.0027", "Bank is already available"),
	E0028(HttpStatus.BAD_REQUEST, "0028", "exception.0028", "Customer is already available"),
	E0029(HttpStatus.BAD_REQUEST, "0029", "exception.0029", "Account is already available"),
	E0030(HttpStatus.BAD_REQUEST, "0030", "exception.0030", "Branch not available")
	;

	private HttpStatus status;
	private String code;
	private String messagekey;
	private String defaultMessage;
	
	private Exceptions(HttpStatus status, String code, String messagekey, String defaultMessage) {
		this.status = status;
		this.code = code;
		this.messagekey = messagekey;
		this.defaultMessage = defaultMessage;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getCode() {
		return code;
	}

	public String getMessagekey() {
		return messagekey;
	}

	public String getDefaultMessage() {
		return defaultMessage;
	}
}


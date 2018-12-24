/**
 * Exception class for accessing empty queues
 *
 */
@SuppressWarnings("serial")
public class EmptyQueueException extends RuntimeException {
	public EmptyQueueException(String mesg) {
		super(mesg);
	}
	
	public EmptyQueueException(Throwable t) {
		super(t);
	}
	
	public EmptyQueueException(String mesg, Throwable t) {
		super(mesg, t);
	}
}
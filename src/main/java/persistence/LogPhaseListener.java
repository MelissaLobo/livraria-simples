package persistence;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class LogPhaseListener implements PhaseListener {

	private static final long serialVersionUID = -2669216854368325499L;

	@Override
	public void afterPhase(PhaseEvent event) {
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		System.out.println("Fase: " + event.getPhaseId());
	}

	@Override
	public PhaseId getPhaseId() {
		return null;
	}

}

package inc.flide.touchboard;

import java.util.ArrayList;
import java.util.List;

public interface Subject {
	List<Observer> observers = new ArrayList<Observer>();
	public void registerObserver(Observer observer);
	public void notifyObservers();
}

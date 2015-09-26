package inc.flide.touchboard.model;

import java.util.ArrayList;
import java.util.List;
import inc.flide.touchboard.view.Observer;

public interface Subject {
	List<Observer> observers = new ArrayList<Observer>();
	public void registerObserver(Observer observer);
	public void notifyObservers();
}

import java.util.LinkedList;
import java.util.Queue;

public class Parkeinweiser implements ParkeinweiserIF{
	
	private Queue<Integer> parkplaetze;
	private int size;
	
	public Parkeinweiser(int s) {
		this.size = s;
		this.parkplaetze = new LinkedList<Integer>();
		for(int i = 0; i < s; i++) {
			this.parkplaetze.add(i + 1);
		}
	}

	@Override
	public int getParkplatz() {
		if(!this.parkplaetze.isEmpty()) {
			return this.parkplaetze.poll();
		} else {
			return -1;
		}
	}

	@Override
	public void returnParkplatz(int p) {
		if(p <= this.size) {
			this.parkplaetze.add(p);
		}
	}

	@Override
	public void changeSize(int newSize) {
		// old and new size
		int oldSize = this.size;
		this.size = newSize;
		// if oldSize < newSize
		if(oldSize < newSize) {
			for(int i = oldSize + 1; i <= newSize; i++) {
				this.parkplaetze.add(i);
			}
		} else if(oldSize > newSize) {
			for(int i = oldSize; i > newSize; i--) {
				if(this.parkplaetze.contains(i)) {
					this.parkplaetze.remove(i);
				}
			}
		}
		
		
	}

	@Override
	public int getSize() {
		return this.size;
	}

}

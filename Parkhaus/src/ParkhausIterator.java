import java.util.Iterator;
import java.util.List;

public class ParkhausIterator implements Iterator<Auto>{
	
	List<Auto> liste;
	int pointer;
	
	public ParkhausIterator(List<Auto> liste) {
		this.pointer = 0;
		for(Auto a: liste) {
			if(a.duration.equals("_") && a.price.equals("_")) {
				this.liste.add(a);
			}
		}
	}

	@Override
	public boolean hasNext() {
		return pointer < this.liste.size();
	}

	@Override
	public Auto next() {
		return this.liste.get(pointer);
	}



}

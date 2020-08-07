import java.util.Iterator;
import java.util.List;

public class ParkhausIterator implements Iterator<AutoIF>{
	
	List<AutoIF> liste;
	int pointer;
	
	public ParkhausIterator(List<AutoIF> liste) {
		this.pointer = 0;
		for(AutoIF a: liste) {
			if(a.getDuration().equals("_") && a.getPrice().equals("_")) {
				this.liste.add(a);
			}
		}
	}

	@Override
	public boolean hasNext() {
		return pointer < this.liste.size();
	}

	@Override
	public AutoIF next() {
		return this.liste.get(pointer);
	}



}

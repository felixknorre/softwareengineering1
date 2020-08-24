import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ParkhausIterator implements Iterator<AutoIF>{
	
	List<AutoIF> liste;
	int pointer;
	
	public ParkhausIterator(List<AutoIF> liste) {
		this.pointer = 0;
		this.liste = liste;
	}

	@Override
	public boolean hasNext() {
		return this.pointer < this.liste.size();
	}

	@Override
	public AutoIF next(){
		AutoIF tmp;
		if(0 <= this.pointer && this.pointer < this.liste.size()) {
			tmp = this.liste.get(this.pointer);
			this.pointer++;
			return tmp;
		} else {
			throw new NoSuchElementException("No Element on Index: " + this.pointer + ", max. Index: " + this.liste.size());
		}
		
	}



}

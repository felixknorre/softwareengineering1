
public interface ParkhausConfigIF {
	
	String toString();
	
	// set method
	void setMaxAutos(String s);
	void setOpen(String s);
	void setClose(String s);
	void setVerzögerung(String s);
	void setPreisFaktor(String s);
	
	// get method
	String getMaxAuto();
	String getOpen();
	String getClose();
	String getVerzögerung();
	String getPreisFaktor();

}

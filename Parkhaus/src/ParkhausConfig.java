
public class ParkhausConfig implements ParkhausConfigIF{
	
	private String maxAuto;
	private String open;
	private String close;
	private String VerzögerungAmpel;
	private String PreisFaktor;
	
	public ParkhausConfig(String m, String o, String c, String v, String p) {
		this.maxAuto = m;
		this.open = o;
		this.close = c;
		this.VerzögerungAmpel = v;
		this.PreisFaktor = p;
	}
	
	public String toString() {
		return this.getMaxAuto() + "," + this.getOpen() + "," + this.getClose() + "," + this.getVerzögerung() + "," + this.getPreisFaktor();
	}

	@Override
	public void setMaxAutos(String s) {
		this.maxAuto = s;
		
	}

	@Override
	public void setOpen(String s) {
		this.open = s;
		
	}

	@Override
	public void setClose(String s) {
		this.close = s;		
	}

	@Override
	public void setVerzögerung(String s) {
		this.VerzögerungAmpel = s;
		
	}

	@Override
	public void setPreisFaktor(String s) {
		this.PreisFaktor = s;
		
	}

	@Override
	public String getMaxAuto() {
		return this.maxAuto;
	}

	@Override
	public String getOpen() {
		return this.open;
	}

	@Override
	public String getClose() {
		return this.close;
	}

	@Override
	public String getVerzögerung() {
		return this.VerzögerungAmpel;
	}

	@Override
	public String getPreisFaktor() {
		return this.PreisFaktor;
	}

}

package kr.or.dgit.bigdata.dto;

public class Title {
	private int tcode;
	private String tname;
	
	public Title() {
	}
	
	public Title(int tcode, String tname) {
		this.tcode = tcode;
		this.tname = tname;
	}

	public int getTcode() {
		return tcode;
	}

	public void setTcode(int tcode) {
		this.tcode = tcode;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}
	
	public static String getStrTcode(int tcode){
		return String.format("T%03d", tcode);
	}

	@Override
	public String toString() {
		return String.format("Title [tcode=%s, tname=%s]", tcode, tname);
	}
	
	public String[] toArray(){
		return new String[]{getStrTcode(tcode), tname};
	}
}

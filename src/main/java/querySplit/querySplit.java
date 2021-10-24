package querySplit;

public class querySplit {
	private String qIndex;
	private String qWork="";
	private int qFlag=0;
	
	public String getQIndex() {
		return qIndex;
	}
	public void setQIndex(String qIndex) {
		if(this.qIndex == null) {
			this.qIndex = qIndex;
		}
		else
			this.qIndex = this.qIndex + qIndex;
	}
	public String getQWork() {
		return qWork;
	}
	public void setQWork(String qWork) {
		if(this.qWork.equals("")) {
			this.qWork = qWork;
		}
		else
			this.qWork = this.qWork + " " + qWork;
	}
	public int getQFlag() {
		return qFlag;
	}
	public void setQFlag(int flag) {
		this.qFlag = flag;
	}
	public void setQFlag(String line) 
	{
			if(line.contains(".I"))
				setQFlag(1);
			else if(line.equals(".W")) {
				setQFlag(2);
					
			}
	}
	public void setValue(String line) {
			switch(getQFlag()) {
			case 1:
				setQIndex(line.split(" ")[1]);
				break;
			case 2:
				if(line.contains(".W")==false)
					setQWork(line);	
				break;
			}
	}
}


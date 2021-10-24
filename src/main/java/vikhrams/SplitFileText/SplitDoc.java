package vikhrams.SplitFileText;


public class SplitDoc {
	private String index;
	private String title="";
	private String author="";
	private String bibliography="";
	private String work="";
	private int flag=0;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if(this.title.equals("")) {
			this.title = title;
		}
		else
			this.title = this.title + " " + title;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String string) {
		if(this.index==null) {
			this.index = string;
		}
		else
			this.index = this.index + index;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		if(this.author.equals("")) {
			this.author = author;
		}
		else
			this.author = this.author + " " + author;
	}

	public String getBibliography() {
		return bibliography;
	}

	public void setBibliography(String bibliography) {
		if(this.bibliography.equals("")) {
			this.bibliography = bibliography;
		}
		else
			this.bibliography = this.bibliography + " " + bibliography;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		if(this.work.equals("")) {
			this.work = work;
		}
		else
			this.work = this.work + " " + work;
	}
	
	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
	//setFlag method reads lines and sets Flag based 
	//on the index key found in file
	public void setFlag(String line) 
	{
			if(line.contains(".I"))
				setFlag(1);
			else if(line.equals(".T")) {
				setFlag(2);
			}
			else if(line.equals(".A")) {
				setFlag(3);
			}
			else if(line.equals(".B")) {
				setFlag(4);
			}
			else if(line.equals(".W")) { 
				setFlag(5);
			}
	}
	//setValue method sets the field value based on flag
	public void setValue(String line) {
			switch(getFlag()) {
			case 1:
				setIndex(line.substring(3));
				break;
			case 2:
				if(line.contains(".T")==false)
					setTitle(line);	
				break;
			case 3:
				if(line.contains(".A")==false)
					setAuthor(line);
				break;
			case 4:
				if(line.contains(".B")==false)
					setBibliography(line);
				break;	
			case 5:
				if(line.contains(".W")==false)
					setWork(line);
				break;
			}
	}
}

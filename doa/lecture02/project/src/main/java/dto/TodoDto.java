package dto;

public class TodoDto {
	private Long id;
	private String name;
	private String regdate;
    private int sequence;
    private String title;
    private String type;

    public TodoDto() {
    	
    }
    
    public TodoDto(Long id, String name, String regdate, int sequence, String title, String type) {
        super();
        this.id = id;
        this.name = name;
        this.regdate = regdate;
        this.sequence = sequence;
        this.title = title;
        this.type = type;
}
    public TodoDto(String title2, String name2, int sequence2) {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
        return id;
    }

    public void setId() {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName() {
        this.name = name;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate() {
        this.regdate = regdate;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence() {
        this.sequence = sequence;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle() {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType() {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Todo [id= " +id + ", name= " +name + ", regdate= " +regdate + ", sequence= " +sequence + ", title= " +title + ", type= " + type + "]";
    }
}
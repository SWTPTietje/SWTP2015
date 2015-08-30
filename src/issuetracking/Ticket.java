package issuetracking;

import java.io.Serializable;
import java.text.*;
import java.util.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tickets")
public class Ticket implements Serializable {
        public static final String BUG = "bug";
        public static final String FEATURE = "feature";
	
        @Id
	protected int id;

	
	protected int sprintid;
	protected String title;
	protected String description;
	protected Date date;
	protected String author;
	protected String responsible_user;
	protected String type;
	protected String state;
        
        private String estimated_time;

        public Ticket() {
        }

	public Ticket(int sprintid, String title, String description,
			Date date, String author, String responsible_user, String type,
			String state) {
		this.sprintid = sprintid;
		this.title = title;
		this.description = description;
		this.date = date;
		this.author = author;
		this.responsible_user = responsible_user;
		this.type = type;
		this.state = state;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getSprintid() {
		return sprintid;
	}

	public void setSprintid(int sprintid) {
		this.sprintid = sprintid;
	}

	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public String getDateAsString() {
   		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
  		String date1=ft.format(date);
		return date1;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getResponsible_user() {
		return responsible_user;
	}

	public void setResponsible_user(String responsible_user) {
		this.responsible_user = responsible_user;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getEstimated_time() {
		return estimated_time;
	}

	public void setEstimated_time(String estimated_time) {
            this.estimated_time = estimated_time;
	}
	
	public Map<String, String> validate(DBManager DBManager1) {
		Map<String, String> errorMsg = new HashMap<String, String>();
		if (title == null || title.trim().equals(""))
			errorMsg.put("title", "This field should not be empty!");
		if (description == null || description.trim().equals(""))
			errorMsg.put("description", "This field should not be empty!");
		if (author == null || author.trim().equals(""))
			errorMsg.put("author", "This field should not be empty!");
		if (!DBManager1.containsUser(author))
			errorMsg.put("author", "The user does not exist!");
		if (responsible_user == null || responsible_user.trim().equals(""))
			errorMsg.put("responsible_user",
					"This field should not be empty!");
		if (!DBManager1.containsUser(responsible_user))
			errorMsg.put("responsible_user", "The user does not exist!");
		if (type == null || type.trim().equals(""))
			errorMsg.put("type", "This field should not be empty!!");
		if (state == null || state.trim().equals(""))
			errorMsg.put("state", "This field should not be empty!!");
                
                if(FEATURE.equals(type)){
                    if (estimated_time == null || estimated_time.trim().equals(""))
			errorMsg.put("estimated_time",
					"estimated_time darf nicht leer sein!");
                }

		return errorMsg;
	}

}

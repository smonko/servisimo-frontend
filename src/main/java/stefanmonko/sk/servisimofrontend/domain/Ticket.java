package stefanmonko.sk.servisimofrontend.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ticket {

	private Integer id;

    private String reporter;

    private Date date;

    private Date deadline;

    private String description;

    private Integer status;

    private Integer severity;

    private String assignee;   

    private String item;
    
    
}

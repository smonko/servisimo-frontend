package stefanmonko.sk.servisimofrontend.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ticket {

	private Integer id;

    private String reporter;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deadline;

    private String description;

    private Integer status;

    private Integer severity;

    private String assignee;   

    private String item;
    
    
}

package hellocalendar.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCalendarRequest {
    private String title;
    private String memo;
    private String start;
    private String end;
    private String color;
}

package hellocalendar.demo.service;

import hellocalendar.demo.domain.MyCalendar;
import hellocalendar.demo.repository.MyCalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class CalendarService {
    private final MyCalendarRepository calendarRepository;

    @Autowired
    public CalendarService(MyCalendarRepository calendarRepository){
        this.calendarRepository = calendarRepository;
    }
    @GetMapping
    public List<MyCalendar> getAllEvents(){
        return calendarRepository.findAll();
    }
    @PostMapping
    public MyCalendar createEvents(MyCalendar myCalendar){
        return calendarRepository.save(myCalendar);
    }


}

package hellocalendar.demo.controller;


import hellocalendar.demo.domain.MyCalendar;
import hellocalendar.demo.repository.MyCalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class CalendarController {

   @Autowired
    private MyCalendarRepository myCalendarRepository;

   @GetMapping
    public List<MyCalendar> getAllEvents(){
       return myCalendarRepository.findAll();
   }
   @PostMapping
    public MyCalendar createEvent(@RequestBody MyCalendar calendar){

       return myCalendarRepository.save(calendar);
   }
    @PutMapping("/{id}")
    public ResponseEntity<MyCalendar> updateEvent(@PathVariable Long id, @RequestBody MyCalendar updateEvent) {
        return myCalendarRepository.findById(id)
                .map(existingEvent -> {
                    existingEvent.setTitle(updateEvent.getTitle());
                    existingEvent.setMemo(updateEvent.getMemo());
                    existingEvent.setStart(updateEvent.getStart());
                    existingEvent.setEnd(updateEvent.getEnd());
                    existingEvent.setColor(updateEvent.getColor());
                    MyCalendar updatedEvent = myCalendarRepository.save(existingEvent);
                    return ResponseEntity.ok(updatedEvent);
                })
                .orElse(ResponseEntity.notFound().build());
    }

   @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id){
       return myCalendarRepository.findById(id)
               .map(existingEvent -> {
                   myCalendarRepository.delete(existingEvent);
                   return ResponseEntity.ok().<Void>build();
               })
               .orElse(ResponseEntity.notFound().build());
   }


}

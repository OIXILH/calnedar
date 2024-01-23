package hellocalendar.demo.repository;

import hellocalendar.demo.domain.MyCalendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyCalendarRepository extends JpaRepository<MyCalendar, Long> {

}

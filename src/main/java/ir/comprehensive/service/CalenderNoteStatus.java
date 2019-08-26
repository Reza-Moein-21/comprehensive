package ir.comprehensive.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CalenderNoteStatus {

    LocalDate creationTime;
    Long activeCount;
    Long inActiveCount;


}

package ru.otveska.yclients.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.otveska.yclients.YClientsConst;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookRecord {

    Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = YClientsConst.DATE_FORMAT)
    Date date;

}

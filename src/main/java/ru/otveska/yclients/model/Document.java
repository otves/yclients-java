package ru.otveska.yclients.model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.otveska.yclients.YClientsConst;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Document {

    Long id;

    @JsonAlias("type_id")
    Long typeId;

    @JsonAlias("storage_id")
    Long storageId;

    @JsonAlias("user_id")
    Long userId;

    @JsonAlias("company_id")
    Long companyId;

    Long number;

    String comment;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = YClientsConst.DATE_FORMAT)
    Date dateCreated;

    @JsonAlias("category_id")
    Integer categoryId;

    @JsonAlias("visit_id")
    Long visitId;

    @JsonAlias("record_id")
    Long recordId;

    @JsonAlias("type_title")
    String typeTitle;
}

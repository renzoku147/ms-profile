package com.spring.msprofile.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;

@Data
@Builder
@Document("SubType")
@AllArgsConstructor
@NoArgsConstructor
public class SubType {
    @Id
    String id;

    @Valid
    EnumSubType value;

    enum EnumSubType{
        NORMAL, VIP, PYME
    }
}

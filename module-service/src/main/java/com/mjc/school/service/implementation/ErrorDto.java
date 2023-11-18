package com.mjc.school.service.implementation;

import com.mjc.school.service.interfaces.ModelDtoInterface;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto implements ModelDtoInterface {
    private String message;
    @Override
    public String toString(){
        return "Error: " + message;
    }
}

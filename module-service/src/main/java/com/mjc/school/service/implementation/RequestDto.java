package com.mjc.school.service.implementation;

import com.mjc.school.service.interfaces.ModelDtoInterface;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto implements ModelDtoInterface {
    private ModelDtoInterface inputData;

    @Override
    public String toString() {
        return "Request{" +
                "inputData=" + inputData +
                '}';
    }
}

package com.mjc.school.service.implementation;

import com.mjc.school.service.interfaces.ModelDtoInterface;
import lombok.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto implements ModelDtoInterface {
    private String status;
    private List<ModelDtoInterface> resultSet;
    private ErrorDto error;

    @Override
    public String toString() {
        return "Response{" +
                "status='" + status + '\'' +
                ", resultSet=" + resultSet +
                ", error=" + error +
                '}';
    }
}

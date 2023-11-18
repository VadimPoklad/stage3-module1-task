package com.mjc.school.service.implementation;

import com.mjc.school.service.interfaces.ModelDtoInterface;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto implements ModelDtoInterface {
    private String id;
    private String name;

    @Override
    public String toString() {
        return "Author{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

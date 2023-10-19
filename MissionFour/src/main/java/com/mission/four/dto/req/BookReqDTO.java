package com.mission.four.dto.req;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookReqDTO {
    private int id;
    @NotEmpty(message = "title은 null일 수 없습니다.")
    private String title;
    @NotEmpty(message = "author은 null일 수 없습니다.")
    private String author;
    @NotEmpty(message = "isbn은 null일 수 없습니다.")
    private String isbn;
    @NotEmpty(message = "genre은 null일 수 없습니다.")
    private String genre;
}

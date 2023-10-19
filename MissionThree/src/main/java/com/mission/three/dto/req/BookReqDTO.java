package com.mission.three.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookReqDTO {
    private String title;
    private String author;
    private String isbn;
    private String genre;
}

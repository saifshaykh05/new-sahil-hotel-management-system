package com.newsahilhotel.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class StandardApiResponse <T>{
    private Boolean status;
    private String message;
    private T data;


}

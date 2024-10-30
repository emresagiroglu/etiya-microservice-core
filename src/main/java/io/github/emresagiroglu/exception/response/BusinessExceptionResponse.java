package io.github.emresagiroglu.exception.response;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessExceptionResponse {
    private int status;
    private String error;
}

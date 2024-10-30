package io.github.emresagiroglu.exception.detail;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
public class ValidationProblemDetails extends ProblemDetails{
  public ValidationProblemDetails(){
    setTitle("Validation Rule Violation");
    setType("");
    setStatus("400");
  }

  private Map<String,String> errors;
}

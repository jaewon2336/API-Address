package site.metacoding.testproject.web.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateReqDto {

    @Size(min = 8, max = 60)
    @NotBlank
    @Email
    private String email;

    @Size(min = 4, max = 100)
    @NotBlank
    private String address;
}

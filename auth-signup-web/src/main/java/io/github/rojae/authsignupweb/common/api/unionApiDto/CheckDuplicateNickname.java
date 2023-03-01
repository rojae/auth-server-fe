package io.github.rojae.authsignupweb.common.api.unionApiDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CheckDuplicateNickname {
    private String nickname;
}

package com.smartjob.managing.profile.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Phone {

    @Schema(name = "number", example = "1234567", required = true)
    private String number;
    @Schema(name = "cityCode", example = "1", required = true)
    private String cityCode;
    @Schema(name = "countryCode", example = "57", required = true)
    private String countryCode;

}

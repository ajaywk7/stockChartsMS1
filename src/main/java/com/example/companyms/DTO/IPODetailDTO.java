package com.example.companyms.DTO;

import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Setter
@Getter
public class IPODetailDTO {
    private Long id;

    @Min(1)
    @NotNull
    private Long companyId;

    @Min(1)
    @NotNull
    private Double pricePerShare;

    @Min(1)
    @NotNull
    @Column(nullable = false)
    private Long totalNumberOfShares;

    @NotNull
    private LocalDateTime openDateTime;
}

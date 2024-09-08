package com.example.iticketfinal.dto.image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageDto {
    private String name;
    private String bucket;
    private String path;
    private LocalDateTime createdAt;
}

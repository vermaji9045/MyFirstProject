package com.School.SchoolValleyProject.Model;


import lombok.Data;

import java.time.LocalDateTime;
@Data
public class BaseEntity {

        private LocalDateTime createdAt;
        private String createdBy;
        private LocalDateTime updatedAt;
        private String updatedBy;

}

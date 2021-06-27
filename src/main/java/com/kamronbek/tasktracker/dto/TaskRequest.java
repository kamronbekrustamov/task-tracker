package com.kamronbek.tasktracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskRequest {
    private String title;
    private String date;
    private String time;
    private Boolean important;
}

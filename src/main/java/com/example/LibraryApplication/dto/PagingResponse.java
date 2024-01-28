package com.example.LibraryApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author Hanan Al-Hajri 2024/01/28
 */

@Data
@AllArgsConstructor
public class PagingResponse<T> {
    private List<T> pageResults;
    private long totalRecords;
    private int totalPages;
}

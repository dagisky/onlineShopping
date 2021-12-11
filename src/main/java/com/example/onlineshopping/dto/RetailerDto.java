package com.example.onlineshopping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetailerDto implements Serializable {
    private  long id;
    private  String firstName;
    private  String lastName;
}

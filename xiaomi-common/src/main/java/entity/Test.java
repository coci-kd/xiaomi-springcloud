package entity;


import lombok.Data;

import java.io.Serializable;

@Data
public class Test implements Serializable {

    private Integer testId;

    private String testName;

}

package entity;


import lombok.Data;

import java.io.Serializable;

@Data
public class UserBean implements Serializable {

    private Integer userId;
    private String account;
    private String password;
    private String phone;

}

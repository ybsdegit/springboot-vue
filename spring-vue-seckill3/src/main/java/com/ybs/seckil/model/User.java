package com.ybs.seckil.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

/**
 * User
 *
 * @author Paulson
 * @date 2020/3/12 19:24
 */
//@Entity
//@Table(name = "user")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private Integer id;
    @Id
    private String username;
    private String password;

//    private String repassword;

}

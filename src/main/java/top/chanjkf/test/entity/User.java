package top.chanjkf.test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * Created by yi on 2018/3/23.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;

    private String userName;

    private String passWord;

    private Date createdTime;

    private Date updatedTime;

    public boolean check() {
        return !StringUtils.isAnyEmpty(this.userName, this.passWord);
    }
}

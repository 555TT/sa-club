package com.xiaoshouwaliang.subject.infra.basic.entity;
import com.xiaoshouwaliang.subject.common.entity.PageInfo;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
/**
 * @author 小手WA凉
 * @date 2024-05-10
 */
@Data
public class SubjectInfoEs  implements Serializable {
    private Long subjectId;

    private Long docId;

    private String subjectName;

    private String subjectAnswer;

    private String createUser;

    private Long createTime;

    private Integer subjectType;

    /**
     * 搜索框里要搜的数据
     */
    private String keyWord;

    /**
     * 相关性越高，分数越高
     */
    private BigDecimal score;

    private Integer pageNo = 1;

    private Integer pageSize = 20;

    public Integer getPageNo() {
        if (pageNo == null || pageNo < 1) {
            return 1;
        }
        return pageNo;
    }

    public Integer getPageSize() {
        if (pageSize == null || pageSize < 1 || pageSize > Integer.MAX_VALUE) {
            return 20;
        }
        return pageSize;
    }
}

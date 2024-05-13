package com.xiaoshouwaliang.subject.infra.basic.entity;
 
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
/**
 * 题目点赞表(SubjectLiked)实体类
 *
 * @author makejava
 * @since 2024-05-12 15:08:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectLiked {
/**
     * 主键
     */ 
    private Long id;
/**
     * 题目id
     */ 
    private Long subjectId;
/**
     * 题目点赞人用户名
     */ 
    private String likeUserId;
/**
     * 点赞状态 1点赞 0未点赞
     */ 
    private Integer status;
/**
     * 创建人
     */ 
    private String createdBy;
/**
     * 创建时间
     */ 
    private Date createdTime;
/**
     * 修改人
     */ 
    private String updateBy;
/**
     * 修改时间
     */ 
    private Date updateTime;
 
    private Integer isDeleted;
}

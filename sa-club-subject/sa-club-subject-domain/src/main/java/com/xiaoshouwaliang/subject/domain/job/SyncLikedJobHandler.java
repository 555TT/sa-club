package com.xiaoshouwaliang.subject.domain.job;
import com.xiaoshouwaliang.subject.domain.service.SubjectLikedDomainService;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**同步点赞信息到mysql任务处理
 * @author 小手WA凉
 * @date 2024-05-12
 */
@Component
@Slf4j
public class SyncLikedJobHandler {
    @Resource
    private SubjectLikedDomainService subjectLikedDomainService;
    @XxlJob("syncLikeJobHandler")
    public void syncLikeJobHandler() throws Exception {
        try {
            subjectLikedDomainService.syncLiked();
//            log.info("1111");
        } catch (Exception e) {
            XxlJobHelper.log("SyncLikedJobHandler.syncLikeJobHandler.error:{}",e.getMessage());
        }
    }
}

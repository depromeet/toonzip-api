package kr.co.antoon.cruiser.facade;

import kr.co.antoon.cruiser.dto.slack.SlackCruiserResponse;
import kr.co.antoon.discussion.application.DiscussionService;
import kr.co.antoon.user.application.UserService;
import kr.co.antoon.webtoon.application.WebtoonService;
import kr.co.antoon.webtoon.application.WebtoonSnapshotService;
import kr.co.antoon.webtoon.domain.vo.ActiveStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CruiserFacade {
    private final UserService userService;
    private final DiscussionService discussionService;
    private final WebtoonService webtoonService;
    private final WebtoonSnapshotService webtoonSnapshotService;

    @Transactional(readOnly = true)
    public String sendStatistics() {
        var userCount = userService.count();
        var discussionCount = discussionService.count();
        var publishWebtoonCount = webtoonService.countByStatus(ActiveStatus.PUBLISH);
        var pauseWebtoonCount = webtoonService.countByStatus(ActiveStatus.PAUSE);
        var webtoonSnapshotCount = webtoonSnapshotService.count();

        return SlackCruiserResponse.dataStatistics(
                userCount,
                discussionCount,
                publishWebtoonCount,
                pauseWebtoonCount,
                webtoonSnapshotCount
        );
    }
}
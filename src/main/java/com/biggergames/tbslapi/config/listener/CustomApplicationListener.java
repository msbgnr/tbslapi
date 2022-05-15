package com.biggergames.tbslapi.config.listener;

import com.biggergames.tbslapi.config.util.DataUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomApplicationListener implements ApplicationListener<ApplicationReadyEvent> {

    private final DataUtil dataUtil;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        log.info("Application started!");
        dataUtil.initTeamStandings();
    }
}
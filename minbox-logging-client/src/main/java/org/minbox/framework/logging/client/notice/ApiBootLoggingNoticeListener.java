/*
 * Copyright [2019] [恒宇少年 - 于起宇]
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 *
 */

package org.minbox.framework.logging.client.notice;

import com.alibaba.fastjson.JSON;
import org.minbox.framework.logging.client.notice.away.ApiBootLogStorageNotice;
import org.minbox.framework.logging.core.ApiBootLog;
import org.minbox.framework.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.scheduling.annotation.Async;

/**
 * ApiBoot Logging Console Notice Listener
 *
 * @author：恒宇少年 - 于起宇
 * <p>
 * DateTime：2019-07-16 15:05
 * Blog：http://blog.yuqiyu.com
 * WebSite：http://www.jianshu.com/u/092df3f77bca
 * Gitee：https://gitee.com/hengboy
 * GitHub：https://github.com/hengboy
 */
public class ApiBootLoggingNoticeListener implements SmartApplicationListener {
    /**
     * logger instance
     */
    static Logger logger = LoggerFactory.getLogger(ApiBootLoggingNoticeListener.class);
    /**
     * ApiBoot Log Storage Notice
     */
    private ApiBootLogStorageNotice apiBootLogStorageNotice;
    /**
     * format console log json
     */
    private boolean formatConsoleLogJson;

    public ApiBootLoggingNoticeListener(ApiBootLogStorageNotice apiBootLogStorageNotice, boolean formatConsoleLogJson) {
        this.apiBootLogStorageNotice = apiBootLogStorageNotice;
        this.formatConsoleLogJson = formatConsoleLogJson;
    }

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return eventType == ApiBootLoggingNoticeEvent.class;
    }

    @Override
    @Async
    public void onApplicationEvent(ApplicationEvent event) {
        ApiBootLoggingNoticeEvent apiBootLoggingNoticeEvent = (ApiBootLoggingNoticeEvent) event;
        ApiBootLog apiBootLog = apiBootLoggingNoticeEvent.getLog();

        logger.debug("Request Uri：{}， Logging：\n{}", apiBootLog.getRequestUri(), formatConsoleLogJson ? JsonUtil.beautifyJson(apiBootLog) : JSON.toJSONString(apiBootLog));

        // notice logging object instance
        apiBootLogStorageNotice.notice(apiBootLog);
    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }
}

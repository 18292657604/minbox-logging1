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

package org.minbox.framework.logging.client.notice.away.support;

import org.minbox.framework.logging.client.notice.ApiBootLogNotice;
import org.minbox.framework.logging.client.notice.away.ApiBootLogStorageNotice;
import org.minbox.framework.logging.core.ApiBootLog;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * ApiBoot Logging Notice Local Support
 *
 * @author：恒宇少年 - 于起宇
 * <p>
 * DateTime：2019-07-16 15:18
 * Blog：http://blog.yuqiyu.com
 * WebSite：http://www.jianshu.com/u/092df3f77bca
 * Gitee：https://gitee.com/hengboy
 * GitHub：https://github.com/hengboy
 */
public class ApiBootLoggingLocalStorageNotice implements ApiBootLogStorageNotice {
    /**
     * ApiBoot Logging Local Notice
     */
    private List<ApiBootLogNotice> apiBootLogNotices;

    public ApiBootLoggingLocalStorageNotice(List<ApiBootLogNotice> apiBootLogNotice) {
        this.apiBootLogNotices = apiBootLogNotice;
    }

    @Override
    public void notice(ApiBootLog apiBootLog) {
        if (!ObjectUtils.isEmpty(apiBootLogNotices)) {
            apiBootLogNotices.stream().forEach(apiBootLogNotice -> apiBootLogNotice.notice(apiBootLog));
        }
    }
}

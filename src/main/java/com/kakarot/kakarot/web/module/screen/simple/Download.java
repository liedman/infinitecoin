/*
 * Copyright (c) 2002-2012 Alibaba Group Holding Limited.
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kakarot.kakarot.web.module.screen.simple;

import static com.alibaba.citrus.util.ObjectUtil.*;
import static com.alibaba.citrus.util.StringEscapeUtil.*;
import static org.apache.commons.lang.StringUtils.*;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.citrus.service.requestcontext.buffered.BufferedRequestContext;
import com.alibaba.citrus.turbine.dataresolver.Param;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ��̬���������ļ���
 *
 * @author Michael Zhou
 */
public class Download {
    @Autowired
    private HttpServletResponse response;

    @Autowired
    private BufferedRequestContext brc;

    public void execute(@Param("filename") String filename) throws Exception {
        // Ϊ����ǿ�û����飬�ر�buffering��������������ʼ�������ǵȴ������ļ��������֪ͨ�û����ء�
        brc.setBuffering(false);

        // ����headers�������ļ�����������us-ascii�ַ�����Ϊ��������ļ��ݳ̶Ȳ�ͬ��
        filename = defaultIfNull(trimToNull(filename), "image") + ".txt";
        filename = "\"" + escapeURL(filename) + "\"";

        response.setHeader("Content-disposition", "attachment; filename=" + filename);

        // ֻҪ��������ȷ��content type����Ϳ������û������κ��ı�������Ƶ����ݣ�
        // HTML��JSON��JavaScript��JPG��PDF��EXCEL�ȡ�
        response.setContentType("text/plain");

        PrintWriter out = response.getWriter();

        for (int i = 0; i < 100; i++) {
            out.flush(); // ������ʾ�û�����

            for (int j = 0; j < 100000; j++) {
                out.print(i);
            }

            out.println();

            Thread.sleep(100); // �����ӳ٣��Լ��������ٶ�
        }
    }
}

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

import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.citrus.service.requestcontext.buffered.BufferedRequestContext;
import com.alibaba.citrus.turbine.dataresolver.Param;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * �����������ʾ������
 * <p/>
 * ������̬WEBҳ���������ģ������ڴ�����������ҳ�棬Ȼ��һ�����ύ�����������������ҳ����ȫ����֮ǰ���û�����ȴ���
 * <p/>
 * �˳���չʾ��һ�ּ���������ҳ��һ�����ɡ�һ��չʾ���û�����Ȼҳ�滹û����ȫ���ɣ����û��Ѿ��ܹ���������ҳ�档
 * �ⲻ���������û������飬Ҳʹ���������ҳ�桢���ء�����������ҳ������ʵ���˲������Ӷ��ӿ�����ʾҳ�����ʱ�䡣
 *
 * @author Michael Zhou
 */
public class Count {
    @Autowired
    private HttpServletResponse response;

    @Autowired
    private BufferedRequestContext brc;

    public void execute(@Param("to") int toNumber) throws Exception {
        // ����ر�buffering��δ��ɵ�ҳ��Żᱻ��ʾ��������ϡ�
        brc.setBuffering(false);

        // ����content type��������Ҫ����charset����ܻ�������ȷ��charset��
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("  <title>Count to " + toNumber + "</title>");
        out.println("</head>");
        out.println("<body>");

        for (int i = 1; i <= toNumber; i++) {
            for (int j = 0; j < 10000; j++) {
                out.print(i);
            }

            out.println();
            out.flush(); // ����ǰ�Ľ��������ʾ���������

            Thread.sleep(1000); // ����ȴ�1�룬��������ʾ��
        }

        out.println("</body>");
        out.println("</html>");
    }
}

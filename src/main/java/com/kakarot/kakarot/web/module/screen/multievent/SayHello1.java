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

package com.kakarot.kakarot.web.module.screen.multievent;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * ���������ʾ����һ��screen�ദ�����¼��ķ�����
 *
 * @author Michael Zhou
 */
public class SayHello1 {
    @Autowired
    private HttpServletResponse response;

    /** �˷����������е�event handler֮ǰִ�С� */
    public void beforeExecution() {
        response.setContentType("text/plain");
    }

    /** �˷����������е�event handler֮��ִ�С� */
    public void afterExecution() throws IOException {
        response.flushBuffer(); // �˵��ò��Ǳ��룬ֻ��������ʾafterExecution��������
    }

    /** Ĭ�����ԣ�Ӣ�� */
    public void doPerform() throws IOException {
        doEnglish();
    }

    /** Ӣ�� */
    public void doEnglish() throws IOException {
        response.getWriter().println("English: Hello");
    }

    /** ���� */
    public void doChinese() throws IOException {
        response.getWriter().println("Chinese: ���");
    }

    /** ���� */
    public void doFrench() throws IOException {
        response.getWriter().println("French: Bonjour");
    }

    /** �������� */
    public void doSpanish() throws IOException {
        response.getWriter().println("Spanish: Hola");
    }
}


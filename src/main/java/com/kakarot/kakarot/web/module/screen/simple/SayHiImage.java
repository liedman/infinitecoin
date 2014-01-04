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

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.citrus.service.requestcontext.buffered.BufferedRequestContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ��̬����������ͼƬ��
 *
 * @author Michael Zhou
 */
public class SayHiImage {
    @Autowired
    private HttpServletResponse response;

    @Autowired
    private BufferedRequestContext brc;

    public void execute() throws Exception {
        // Ϊ�˽�ʡ�ڴ棬�ر�buffering��
        brc.setBuffering(false);

        // ֻҪ��������ȷ��content type����Ϳ�������κ��ı�������Ƶ����ݣ�
        // HTML��JSON��JavaScript��JPG��PDF��EXCEL�ȡ�
        response.setContentType("image/jpeg");

        OutputStream out = response.getOutputStream();

        writeImage(out);
    }

    private void writeImage(OutputStream out) throws IOException {
        BufferedImage img = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = img.createGraphics();

        g2d.setPaint(Color.red);
        g2d.setFont(new Font("Serif", Font.BOLD, 36));
        g2d.drawString("Hi there, how are you doing today?", 5, g2d.getFontMetrics().getHeight());
        g2d.dispose();

        ImageIO.write(img, "jpg", out);
    }
}

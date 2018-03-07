package com.yazao.spring.service;

import com.yazao.spring.utils.PDFUtil;

public class PDFService {

    public void createPDF(String path, String input) {
        PDFUtil.createDocument(path, input);
    }
}

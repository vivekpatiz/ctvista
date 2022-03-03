package com.danaherdigital.che_hx.report_management.service;

import java.io.File;
import java.io.IOException;

public interface DocumentService {

	File getExcelDocumentTemplate(String data, String type) throws IOException;

	File getS3Document(String docName);

}

package com.raylinetech.ssh2.logistics.common.service;

import java.util.List;

import com.raylinetech.ssh2.logistics.common.entity.PrintLable;
import com.raylinetech.ssh2.logistics.common.entity.RLOrder;

public interface PdfService_v1 {

	public boolean printLablesToPdf(List<PrintLable> printLables,String path,String fileName);
	
}

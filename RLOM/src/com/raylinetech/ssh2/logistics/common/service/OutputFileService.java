package com.raylinetech.ssh2.logistics.common.service;

import java.util.List;

import com.raylinetech.ssh2.logistics.common.entity.OutputFile;



public interface OutputFileService {
	public OutputFile find(long id);

	public void save(OutputFile user);

	public void modify(OutputFile user);
	
	public boolean deleteOutputFile(OutputFile user) ;
}

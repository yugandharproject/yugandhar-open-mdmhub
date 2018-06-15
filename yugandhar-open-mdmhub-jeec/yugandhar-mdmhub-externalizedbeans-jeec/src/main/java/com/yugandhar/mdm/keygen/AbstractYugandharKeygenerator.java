package com.yugandhar.mdm.keygen;

import java.util.UUID;

public class AbstractYugandharKeygenerator {
	
	public String generateKey(){
	return UUID.randomUUID().toString();
	}

}

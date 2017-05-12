package kr.or.dgit.setting.service;

import kr.or.dgit.setting.Config;

public abstract class ServiceSetting {
	
    protected static String getFilePath(String tableName, boolean isImport) {
        StringBuilder sb = new StringBuilder();
        
        sb.append(isImport ? Config.IMPORT_DIR : Config.MYSQL_EXPORT_PATH).append(tableName).append(".txt");
        return sb.toString().replace("\\", "/");
    }   

	public abstract void initSetting();
}

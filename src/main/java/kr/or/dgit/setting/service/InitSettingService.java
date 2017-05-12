package kr.or.dgit.setting.service;

import kr.or.dgit.setting.Config;
import kr.or.dgit.setting.dao.DataBaseDao;
import kr.or.dgit.setting.dao.TableDao;
import kr.or.dgit.setting.dao.UserDao;

public class InitSettingService extends ServiceSetting {

	@Override
	public void initSetting() {
		createDataBase();
		createTable();
		createUser();
	}

	private void createDataBase() {
		DataBaseDao dao = DataBaseDao.getInstance();
		dao.createDatabase();
		dao.selectUseDatabase();
	}
	
	private void createTable() {
		TableDao dao = TableDao.getInstance();
		for (int i = 0; i < Config.TABLE_NAME.length; i++) {
			dao.createTable(Config.CREATE_SQL[i]);
		}
	}
	
	private void createUser() {
		UserDao.getInstance().initUser();
	}
}

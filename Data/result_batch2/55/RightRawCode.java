// https://github.com/MyCATApache/Mycat-Server/tree/243539fb74bbdcb9819fecc7e7b50ccf0899e671/src/main/java/io/mycat/util/dataMigrator/DataMigrator.java#L69-L105
public class TempClass {
		System.out.println("\n"+format.format(new Date())+" [1]-> creating migrator schedule and temp files for migrate...");
		//初始化配置
		DataMigrator migrator = new DataMigrator(args);
		
		//生成中间文件
		migrator.createTempFiles();
		migrator.changeSize();
		migrator.printInfo();

		//迁移数据
		System.out.println("\n"+format.format(new Date())+" [2]-> start migrate data...");
		migrator.migrateData();
		
		//清除中间临时文件、清除被迁移掉的冗余数据
		System.out.println("\n"+format.format(new Date())+" [3]-> cleaning redundant data...");
		migrator.clear();
		
		//校验数据是否迁移成功
		System.out.println("\n"+format.format(new Date())+" [4]-> validating tables migrate result...");
		migrator.validate();
		migrator.clearTempFiles();
		long end = System.currentTimeMillis();
		System.out.println("\n"+format.format(new Date())+" migrate data complete in "+(end-start)+"ms");
	}
	
	//打印各个表的迁移数据信息
	private void printInfo() {
		for(TableMigrateInfo table:migrateTables){
			table.printMigrateInfo();
			table.printMigrateSchedule();
		}
	}

	//删除临时文件
	private void clearTempFiles() {
		File tempFileDir = new File(margs.getTempFileDir());
		if(tempFileDir.exists() && margs.isDeleteTempDir()){

}
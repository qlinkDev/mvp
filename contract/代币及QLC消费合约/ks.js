contract SzzcRecordContract {

	// 自定义类型：数字资产交易记录
	struct SzzcRecord {
    	address record_address;		// 记录所属地址
		uint record_type;   		// 记录类型(0_增加,1_减少)
		uint amount;   				// QLC数量
		uint time;   				// 发生时间(秒)
	}
	
    // 一个存储`SzzcRecord`结构的映射
    uint public szzcRecordId;
    mapping (uint => SzzcRecord) public szzcRecords;
    
    function szzcRecordIdAdd() {   		
   		// 存储
   		szzcRecordId ++;
    }
    
    function szzcRecordAdd(address record_address, uint record_type, uint amount, uint time) {   		
   		// 存储
   		szzcRecordId = szzcRecordId + 1;
   		szzcRecords[szzcRecordId] = SzzcRecord(record_address, record_type, amount, time);
    }
    
    function szzcRecordAdd1(address record_address, uint record_type, uint amount, uint time) {   		
   		// 存储
   		szzcRecords[szzcRecordId] = SzzcRecord(record_address, record_type, amount, time);
    }
	
    // 存储数字资产交易记录
    function szzcRecordAdd2(address record_address, uint record_type, uint amount, uint time) {   		
   		// 存储
   		szzcRecords[5] = SzzcRecord(record_address, record_type, amount, time);
    }
    
    function szzcRecordAdd3(address record_address, uint record_type, uint amount, uint time) {   		
   		// 存储
		szzcRecords[6] = SzzcRecord({
			record_address:record_address,
			record_type:record_type,
			amount:amount,
			time:time
		});
    }
    
    function szzcRecordAdd4(uint amount, uint time) {   		
   		// 存储
		szzcRecords[10] = SzzcRecord({
			record_address:'0x9F394C1BC92d07d56Ef817b63f204a8492b03445',
			record_type:1,
			amount:amount,
			time:time
		});
    }
}
contract SzzcRecordContract {

	// �Զ������ͣ������ʲ����׼�¼
	struct SzzcRecord {
    	address record_address;		// ��¼������ַ
		uint record_type;   		// ��¼����(0_����,1_����)
		uint amount;   				// QLC����
		uint time;   				// ����ʱ��(��)
	}
	
    // һ���洢`SzzcRecord`�ṹ��ӳ��
    uint public szzcRecordId;
    mapping (uint => SzzcRecord) public szzcRecords;
    
    function szzcRecordIdAdd() {   		
   		// �洢
   		szzcRecordId ++;
    }
    
    function szzcRecordAdd(address record_address, uint record_type, uint amount, uint time) {   		
   		// �洢
   		szzcRecordId = szzcRecordId + 1;
   		szzcRecords[szzcRecordId] = SzzcRecord(record_address, record_type, amount, time);
    }
    
    function szzcRecordAdd1(address record_address, uint record_type, uint amount, uint time) {   		
   		// �洢
   		szzcRecords[szzcRecordId] = SzzcRecord(record_address, record_type, amount, time);
    }
	
    // �洢�����ʲ����׼�¼
    function szzcRecordAdd2(address record_address, uint record_type, uint amount, uint time) {   		
   		// �洢
   		szzcRecords[5] = SzzcRecord(record_address, record_type, amount, time);
    }
    
    function szzcRecordAdd3(address record_address, uint record_type, uint amount, uint time) {   		
   		// �洢
		szzcRecords[6] = SzzcRecord({
			record_address:record_address,
			record_type:record_type,
			amount:amount,
			time:time
		});
    }
    
    function szzcRecordAdd4(uint amount, uint time) {   		
   		// �洢
		szzcRecords[10] = SzzcRecord({
			record_address:'0x9F394C1BC92d07d56Ef817b63f204a8492b03445',
			record_type:1,
			amount:amount,
			time:time
		});
    }
}
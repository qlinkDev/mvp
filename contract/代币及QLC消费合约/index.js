var Web3 = require("web3");
// ����web3����
var web3 = new Web3();
// ���ӵ���̫���ڵ�
web3.setProvider(new Web3.providers.HttpProvider("http://localhost:8545"));
// ��ԼABI
var abi = [ { "constant": false, "inputs": [ { "name": "newSellPrice", "type": "uint256" }, { "name": "newBuyPrice", "type": "uint256" } ], "name": "setPrices", "outputs": [], "payable": false, "type": "function" }, { "constant": true, "inputs": [], "name": "name", "outputs": [ { "name": "", "type": "string", "value": "QLC_B" } ], "payable": false, "type": "function" }, { "constant": false, "inputs": [ { "name": "_spender", "type": "address" }, { "name": "_value", "type": "uint256" } ], "name": "approve", "outputs": [ { "name": "success", "type": "bool" } ], "payable": false, "type": "function" }, { "constant": true, "inputs": [], "name": "totalSupply", "outputs": [ { "name": "", "type": "uint256", "value": "1e+27" } ], "payable": false, "type": "function" }, { "constant": false, "inputs": [ { "name": "_from", "type": "address" }, { "name": "_to", "type": "address" }, { "name": "_value", "type": "uint256" } ], "name": "transferFrom", "outputs": [ { "name": "success", "type": "bool" } ], "payable": false, "type": "function" }, { "constant": true, "inputs": [], "name": "decimals", "outputs": [ { "name": "", "type": "uint8", "value": "18" } ], "payable": false, "type": "function" }, { "constant": false, "inputs": [ { "name": "_value", "type": "uint256" } ], "name": "burn", "outputs": [ { "name": "success", "type": "bool" } ], "payable": false, "type": "function" }, { "constant": false, "inputs": [ { "name": "record_address", "type": "address" }, { "name": "record_type", "type": "uint256" }, { "name": "amount", "type": "uint256" }, { "name": "time", "type": "uint256" } ], "name": "szzcRecordAdd", "outputs": [ { "name": "", "type": "uint256" } ], "payable": false, "type": "function" }, { "constant": true, "inputs": [ { "name": "", "type": "address" } ], "name": "szzcBalanceOf", "outputs": [ { "name": "", "type": "uint256", "value": "0" } ], "payable": false, "type": "function" }, { "constant": true, "inputs": [], "name": "sellPrice", "outputs": [ { "name": "", "type": "uint256", "value": "0" } ], "payable": false, "type": "function" }, { "constant": true, "inputs": [ { "name": "", "type": "address" } ], "name": "balanceOf", "outputs": [ { "name": "", "type": "uint256", "value": "0" } ], "payable": false, "type": "function" }, { "constant": false, "inputs": [ { "name": "target", "type": "address" }, { "name": "mintedAmount", "type": "uint256" } ], "name": "mintToken", "outputs": [], "payable": false, "type": "function" }, { "constant": false, "inputs": [ { "name": "_from", "type": "address" }, { "name": "_value", "type": "uint256" } ], "name": "burnFrom", "outputs": [ { "name": "success", "type": "bool" } ], "payable": false, "type": "function" }, { "constant": true, "inputs": [], "name": "deviceFlowRecordId", "outputs": [ { "name": "", "type": "uint256", "value": "0" } ], "payable": false, "type": "function" }, { "constant": true, "inputs": [], "name": "buyPrice", "outputs": [ { "name": "", "type": "uint256", "value": "0" } ], "payable": false, "type": "function" }, { "constant": false, "inputs": [ { "name": "_from", "type": "address" }, { "name": "_to", "type": "address" }, { "name": "_value", "type": "uint256" } ], "name": "rechargeDataDictionary1", "outputs": [ { "name": "success", "type": "bool" } ], "payable": false, "type": "function" }, { "constant": false, "inputs": [ { "name": "_from", "type": "address" }, { "name": "_to", "type": "address" }, { "name": "_value", "type": "uint256" } ], "name": "rechargeDataDictionary2", "outputs": [ { "name": "success", "type": "bool" } ], "payable": false, "type": "function" }, { "constant": false, "inputs": [ { "name": "imei", "type": "bytes32" }, { "name": "user_address", "type": "address" }, { "name": "time", "type": "uint256" }, { "name": "used_flow", "type": "uint256" }, { "name": "flow_ratio", "type": "uint256" }, { "name": "time_ratio", "type": "uint256" }, { "name": "time_sign", "type": "uint256" } ], "name": "deviceFlowRecordAdd", "outputs": [ { "name": "", "type": "uint256" } ], "payable": false, "type": "function" }, { "constant": true, "inputs": [], "name": "owner", "outputs": [ { "name": "", "type": "address", "value": "0x56e4db37542a492e62c4c1a5b4acbdc85b043894" } ], "payable": false, "type": "function" }, { "constant": true, "inputs": [], "name": "symbol", "outputs": [ { "name": "", "type": "string", "value": "QLCB" } ], "payable": false, "type": "function" }, { "constant": false, "inputs": [], "name": "buy", "outputs": [], "payable": true, "type": "function" }, { "constant": false, "inputs": [ { "name": "_to", "type": "address" }, { "name": "_value", "type": "uint256" } ], "name": "transfer", "outputs": [], "payable": false, "type": "function" }, { "constant": true, "inputs": [ { "name": "", "type": "address" } ], "name": "frozenAccount", "outputs": [ { "name": "", "type": "bool", "value": false } ], "payable": false, "type": "function" }, { "constant": true, "inputs": [ { "name": "", "type": "uint256" } ], "name": "deviceFlowRecords", "outputs": [ { "name": "imei", "type": "bytes32", "value": "0x0000000000000000000000000000000000000000000000000000000000000000" }, { "name": "user_address", "type": "address", "value": "0x0000000000000000000000000000000000000000" }, { "name": "used_flow", "type": "uint256", "value": "0" }, { "name": "time", "type": "uint256", "value": "0" }, { "name": "flow_ratio", "type": "uint256", "value": "0" }, { "name": "time_ratio", "type": "uint256", "value": "0" }, { "name": "flow_sign", "type": "uint256", "value": "0" }, { "name": "time_sign", "type": "uint256", "value": "0" } ], "payable": false, "type": "function" }, { "constant": true, "inputs": [], "name": "szzcRecordId", "outputs": [ { "name": "", "type": "uint256", "value": "0" } ], "payable": false, "type": "function" }, { "constant": false, "inputs": [ { "name": "_spender", "type": "address" }, { "name": "_value", "type": "uint256" }, { "name": "_extraData", "type": "bytes" } ], "name": "approveAndCall", "outputs": [ { "name": "success", "type": "bool" } ], "payable": false, "type": "function" }, { "constant": true, "inputs": [ { "name": "", "type": "uint256" } ], "name": "szzcRecords", "outputs": [ { "name": "record_address", "type": "address", "value": "0x0000000000000000000000000000000000000000" }, { "name": "record_type", "type": "uint256", "value": "0" }, { "name": "amount", "type": "uint256", "value": "0" }, { "name": "time", "type": "uint256", "value": "0" } ], "payable": false, "type": "function" }, { "constant": true, "inputs": [ { "name": "", "type": "address" }, { "name": "", "type": "address" } ], "name": "allowance", "outputs": [ { "name": "", "type": "uint256", "value": "0" } ], "payable": false, "type": "function" }, { "constant": false, "inputs": [ { "name": "amount", "type": "uint256" } ], "name": "sell", "outputs": [], "payable": false, "type": "function" }, { "constant": false, "inputs": [ { "name": "target", "type": "address" }, { "name": "freeze", "type": "bool" } ], "name": "freezeAccount", "outputs": [], "payable": false, "type": "function" }, { "constant": false, "inputs": [ { "name": "_from", "type": "address" }, { "name": "_to", "type": "address" }, { "name": "_value", "type": "uint256" } ], "name": "rechargeDataDictionary", "outputs": [ { "name": "success", "type": "bool" } ], "payable": false, "type": "function" }, { "constant": false, "inputs": [ { "name": "newOwner", "type": "address" } ], "name": "transferOwnership", "outputs": [], "payable": false, "type": "function" }, { "inputs": [ { "name": "initialSupply", "type": "uint256", "index": 0, "typeShort": "uint", "bits": "256", "displayName": "initial Supply", "template": "elements_input_uint", "value": "1000000000" }, { "name": "tokenName", "type": "string", "index": 1, "typeShort": "string", "bits": "", "displayName": "token Name", "template": "elements_input_string", "value": "QLC_B" }, { "name": "tokenSymbol", "type": "string", "index": 2, "typeShort": "string", "bits": "", "displayName": "token Symbol", "template": "elements_input_string", "value": "QLCB" } ], "payable": false, "type": "constructor" }, { "anonymous": false, "inputs": [ { "indexed": false, "name": "target", "type": "address" }, { "indexed": false, "name": "frozen", "type": "bool" } ], "name": "FrozenFunds", "type": "event" }, { "anonymous": false, "inputs": [ { "indexed": true, "name": "from", "type": "address" }, { "indexed": true, "name": "to", "type": "address" }, { "indexed": false, "name": "value", "type": "uint256" } ], "name": "Transfer", "type": "event" }, { "anonymous": false, "inputs": [ { "indexed": true, "name": "from", "type": "address" }, { "indexed": false, "name": "value", "type": "uint256" } ], "name": "Burn", "type": "event" } ];
// ��Լ��ַ
var address = "0x771Bec61Fa402D4322A42884ECc317BF105cA264";
// ͨ��ABI�͵�ַ��ȡ�Ѳ���ĺ�Լ����
var metacoin = web3.eth.contract(abi).at(address);

//var account_one = web3.eth.accounts[0];
//console.log("account one balance: ", account_one);

// ��Լ��������(�������Կ�������) OK
//var name = metacoin.name.call();
//console.log("name: ", name);
//var decimals = metacoin.decimals.call();
//console.log("decimals: ", decimals.toNumber());
//var symbol = metacoin.symbol.call();
//console.log("symbol: ", symbol);
//var totalSupply = metacoin.totalSupply.call();
//console.log("totalSupply: ", totalSupply.toNumber());
//var sellPrice = metacoin.sellPrice.call();
//console.log("sellPrice: ", sellPrice.toNumber());

// ���鿴 OK
//var balance = metacoin.balanceOf.call(web3.eth.accounts[1]);
//console.log("balance: ", balance.toNumber());
//balance = metacoin.szzcRecords;
//console.log("balance: ", balance);

// transfer ���� OK
//var res = web3.personal.unlockAccount(web3.eth.accounts[0], "12345678");
//console.log("res: ", res);
//res = metacoin.transfer.sendTransaction("0x9F394C1BC92d07d56Ef817b63f204a8492b03445", 1000000000000000000000, {from: web3.eth.accounts[0]});
//console.log("res: ", res);

// approve ���� OK
// ��Ȩ_spender��ַ���Բ���msg.sender�˻����������Ϊ_value��token��{allowance[msg.sender][_spender],msg.sender����fromֵ},һ����Ȩ�û�����ʹ���û��������token
//var res = web3.personal.unlockAccount("0x9F394C1BC92d07d56Ef817b63f204a8492b03445", "12345678");
//console.log("res: ", res);
//res = metacoin.approve.sendTransaction("0x9F394C1BC92d07d56Ef817b63f204a8492b03445", 10000000000000000000000, {from: web3.eth.accounts[1]});
//console.log("res: ", res);

// transferFrom ���� OK//
// ע����ָ������token��_from�˻�ת�Ƶ�_to�˻���������Ǹ�����ĳ���ܺ�Լ��ʹ�õģ�_from�˻�������Ȩ�ô����в������˻���token��Ȩ������Ȩ����ͨ��approve�ӿ�
//var res = web3.personal.unlockAccount("0x9F394C1BC92d07d56Ef817b63f204a8492b03445", "12345678");
//console.log("res: ", res);
//var res = metacoin.transferFrom.sendTransaction("0x9F394C1BC92d07d56Ef817b63f204a8492b03445", "0x4202a5fE8FCbf17F13b9e6ed7f927F32216dCb56", 200000000000000000000, {from: web3.eth.accounts[1]});
//console.log("res: ", res);

// ��ֵ�����ʲ� ����
//var res = web3.personal.unlockAccount("0x9F394C1BC92d07d56Ef817b63f204a8492b03445", "12345678");
//console.log("res: ", res);
//res = metacoin.rechargeDataDictionary1.sendTransaction("0x9F394C1BC92d07d56Ef817b63f204a8492b03445", "0x4202a5fE8FCbf17F13b9e6ed7f927F32216dCb56", 100000000000000000000, {from: web3.eth.accounts[1]});
//console.log("res: ", res);

// mintToken ���� OK
//var res = web3.personal.unlockAccount(web3.eth.accounts[0], "12345678");
//console.log("res: ", res);
//metacoin.mintToken.sendTransaction(web3.eth.accounts[0], 1000000000000000000, {from: web3.eth.accounts[0]});
//console.log("res: ", res);

// szzcRecordAdd ����
var res = web3.personal.unlockAccount(web3.eth.accounts[1], "12345678");
console.log("res: ", res);
res = metacoin.szzcRecordAdd.sendTransaction("0x9F394C1BC92d07d56Ef817b63f204a8492b03445", 0, 100000000000000000000, 2222222222, {from: web3.eth.accounts[1]});
console.log("res: ", res);
// szzcRecords ȡ����s);

// szzcRecordAdd ����
//var res = web3.personal.unlockAccount(web3.eth.accounts[0], "12345678");
//console.log("res: ", res);
//res = metacoin.szzcRecordAdd.sendTransaction(1, 10, 1511934462, {from: web3.eth.accounts[0]});
//console.log("res: ", res);

//var szzcRecordId = metacoin.szzcRecordId.call();
//console.log("szzcRecordId: ", szzcRecordId.toNumber());

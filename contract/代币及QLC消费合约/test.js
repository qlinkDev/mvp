pragma solidity ^0.4.13;
    
contract MyToken {
    /* This creates an array with all balances */
    mapping (address => uint256) public balanceOf;

    function MyToken(uint256 initialSupply) {
        balanceOf[msg.sender] = initialSupply;
    }
        
	/* Send coins */
    function transfer(address _to, uint256 _value) {
        /* Add and subtract new balances */
        balanceOf[msg.sender] -= _value;
        balanceOf[_to] += _value;
    }
}
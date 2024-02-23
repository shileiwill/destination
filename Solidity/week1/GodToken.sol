// SPDX-License-Identifier: MIT
pragma solidity ^0.8.20;

contract GodToken {
  // Token with god mode. A special address is able to transfer tokens between addresses at will.
  uint public currentSupply;
  string public name = "Our Awesome God Token";
  string public symbol = "God";
  uint public decimals = 18;
  
  address public admin;

  mapping(address => uint) public balanceOf;

  event BanAddress(address addressToBan);

  constructor(address _admin) {
    admin = _admin;
  }

  modifier onlyAdmin() {
    require(msg.sender == admin, "only admin allowed to run this function");
    _;
  }

  function mint(uint amount) public {
    // add the amount to the creator
    balanceOf[msg.sender] += amount;
    // currentSupply should increase
    currentSupply += amount;
  }

  function adminTransfer(address from, address to, uint256 amount) external onlyAdmin {
    require(balanceOf[from] >= amount, "from address doesnt have enough token");
    transferFrom(from, to, amount);
  }

  // burn
  function burn(uint amount) public {
    // remove the amount from the sender
    balanceOf[msg.sender] -= amount;
    // currentSupply should decrease
    currentSupply -= amount;
  }

  // transfer from tx sender to destination
  function transfer(address to, uint amount) public returns (bool) {
    // remove the amount from from_addr
    balanceOf[msg.sender] -= amount;
    // add the amount to to_addr
    balanceOf[to] += amount;

    return true;
  }

  // admin 0x4B20993Bc481177ec7E8f571ceCaE8A9e22C02db
  // mint 10: 0x78731D3Ca6b7E34aC0F824c42a7cC18A495cabaB
  // receive4: 0x617F2E2fD72FD9D5503197092aC168c91465E7f2
  // 0x78731D3Ca6b7E34aC0F824c42a7cC18A495cabaB, 0x617F2E2fD72FD9D5503197092aC168c91465E7f2, 1

  // transfer from A to B
  function transferFrom(address from, address to, uint amount) public returns (bool) {
    require(msg.sender == from || msg.sender == admin, "only owner or admin can do the transfer");
    // remove the amount from from_addr
    balanceOf[from] -= amount;
    // add the amount to to_addr
    balanceOf[to] += amount;

    return true;
  }
}
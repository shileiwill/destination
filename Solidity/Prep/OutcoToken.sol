// SPDX-License-Identifier: MIT
pragma solidity ^0.8.20;

contract OutcoToken {
  
  uint public currentSupply;
  string public name = "Our Awesome Outco Token";
  string public symbol = "OutCo";
  uint public decimals = 18;
  // maxSupply

  mapping(address => uint) public balanceOf;

  function mint(uint amount) public {
    // add the amount to the creator
    balanceOf[msg.sender] += amount;
    // currentSupply should increase
    currentSupply += amount;
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

  // transfer from A to B
}
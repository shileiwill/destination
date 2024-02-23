// SPDX-License-Identifier: MIT
pragma solidity ^0.8.20;

contract BanToken {
  // Token with sanctions. Create a fungible token that allows an admin to ban specified addresses from sending and receiving tokens.
  uint public currentSupply;
  string public name = "Our Awesome Ban Token";
  string public symbol = "Ban";
  uint public decimals = 18;
  
  address public admin;

  mapping(address => uint) public balanceOf;
  mapping(address => bool) public banOf;

  event BanAddress(address addressToBan);

  constructor(address _admin) {
    admin = _admin;
  }

  modifier onlyAdmin() {
    require(msg.sender == admin, "only the admin can call this function");
    _;
  }

  modifier notBanned(address _address) {
    require(!banOf[_address], "address is banned");
    _;
  }

  function ban(address addressToBan) external onlyAdmin notBanned(addressToBan) {
    // require(!banOf[addressToBan], "address is already banned");
    banOf[addressToBan] = true;

    emit BanAddress(addressToBan);
  }

  function mint(uint amount) public notBanned(msg.sender) {
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
  function transfer(address to, uint amount) public notBanned(to) notBanned(msg.sender) returns (bool) {
    // remove the amount from from_addr
    balanceOf[msg.sender] -= amount;
    // add the amount to to_addr
    balanceOf[to] += amount;

    return true;
  }

  // transfer from A to B
  function transferFrom(address from, address to, uint amount) public notBanned(from) notBanned(to) returns (bool) {
    // TODO update allowance
    // remove the amount from from_addr
    balanceOf[from] -= amount;
    // add the amount to to_addr
    balanceOf[to] += amount;

    return true;
  }
}